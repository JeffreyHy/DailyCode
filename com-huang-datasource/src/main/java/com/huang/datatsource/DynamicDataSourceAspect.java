package com.huang.datatsource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * aop
 *
 * @author JeffreyHy
 * @date Created by  2018/1/24 18:30
 */
@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
public class DynamicDataSourceAspect {
    protected static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * 切换前校验
     *
     * @param point
     * @param targetDataSource
     * @return
     */
    public boolean preValidate(JoinPoint point, TargetDataSource targetDataSource) {
        return Boolean.TRUE;
    }

    /**
     * 切换数据源
     *
     * @param point
     * @param targetDataSource
     * @throws Throwable
     */
    @Before("@annotation(targetDataSource)")
    private void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable {
        String dsName = targetDataSource.name();
        if (dsName == null || dsName.length() <= 0) {
            DynamicDataSourceContextHolder.setDefaultDataSourceType();
            return;
        }
        if (!DynamicDataSourceContextHolder.containsDataSource(dsName)) {
            logger.warn("DataSource not exist，use default {} > {}", dsName, point.getSignature());
            DynamicDataSourceContextHolder.setDefaultDataSourceType();
            return;
        }
        if (!preValidate(point, targetDataSource)) {
            return;
        }
        // 非DefaultDataSource查询info日志
        if (!DynamicDataSourceContextHolder.isDefaultDataSource(dsName)) {
            logger.info("change DataSource salve : {} > {}", dsName, point.getSignature());
        }
        DynamicDataSourceContextHolder.setDataSourceType(dsName);
    }

    /**
     * 恢复默认数据源
     *
     * @param point
     * @param targetDataSource
     */
    @After("@annotation(targetDataSource)")
    private void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
