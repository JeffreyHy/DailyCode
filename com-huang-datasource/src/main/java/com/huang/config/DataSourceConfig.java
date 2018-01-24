package com.huang.config;

import com.google.common.collect.ImmutableMap;
import com.huang.constant.DataSourceConstant;
import com.huang.datatsource.DynamicDataSource;
import com.huang.datatsource.DynamicDataSourceAspect;
import com.huang.datatsource.DynamicDataSourceContextHolder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author JeffreyHy
 * @date Created by  2018/1/24 18:30
 */
@Configuration
@MapperScan(basePackages = {DataSourceConstant.MAPPER_PACKAGES}, sqlSessionFactoryRef = DataSourceConstant.SESSION_FACTORY_NAME)
public class DataSourceConfig {
    @Resource
    private Environment env;

    @Bean(name = DataSourceConstant.DATASOURCE_NAME_MASTER)
    @Primary
    @ConfigurationProperties(prefix = DataSourceConstant.PROFILE_PREFIX_MASTER)
    public DataSource dataSourceMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSourceConstant.DATASOURCE_NAME_SLAVE)
    @ConfigurationProperties(prefix = DataSourceConstant.PROFILE_PREFIX_SLAVE)
    public DataSource dataSourceSlave() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSourceConstant.DATASOURCE_NAME)
    public DataSource dataSource(@Qualifier(DataSourceConstant.DATASOURCE_NAME_MASTER) DataSource master,
                                 @Qualifier(DataSourceConstant.DATASOURCE_NAME_SLAVE) DataSource slave) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(master);
        dynamicDataSource.setTargetDataSources(ImmutableMap.of(DataSourceConstant.DATASOURCE_NAME_MASTER, master,
                DataSourceConstant.DATASOURCE_NAME_SLAVE, slave));

        DynamicDataSourceContextHolder.setDefaultDataSource(DataSourceConstant.DATASOURCE_NAME_MASTER);
        DynamicDataSourceContextHolder.addDataSource(DataSourceConstant.DATASOURCE_NAME_MASTER);
        DynamicDataSourceContextHolder.addDataSource(DataSourceConstant.DATASOURCE_NAME_SLAVE);
        return dynamicDataSource;
    }

    @Bean(name = DataSourceConstant.TRANSACTION_MANAGER_NAME)
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier(DataSourceConstant.DATASOURCE_NAME) DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    @Bean(name = DataSourceConstant.SESSION_FACTORY_NAME)
    @Primary
    public SqlSessionFactory initSqlSessionFactory(@Qualifier(DataSourceConstant.DATASOURCE_NAME) DataSource datasource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(env.getProperty(DataSourceConstant.MAPPER_LOCATIONS)));
        return sessionFactory.getObject();
    }

    @Bean(name = DataSourceConstant.JDBC_TEMPLATE_NAME)
    @Primary
    public JdbcTemplate initJdbcTemplate(@Qualifier(DataSourceConstant.DATASOURCE_NAME) DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    @Bean
    @Primary
    public DynamicDataSourceAspect initDataSourceAspect() {
        return new DynamicDataSourceAspect();
    }
}
