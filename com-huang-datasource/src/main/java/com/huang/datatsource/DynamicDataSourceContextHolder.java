
package com.huang.datatsource;

import java.util.HashSet;
import java.util.Set;

/**
 * datasource holder
 *
 * @author JeffreyHy
 * @date Created by  2018/1/24 18:30
 */
public final class DynamicDataSourceContextHolder {
    private DynamicDataSourceContextHolder() {
    }

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
        /**
         * 将 master 数据源的 key 作为默认数据源的 key
         */
        @Override
        protected String initialValue() {
            return defaultDataSource;
        }
    };

    private static String defaultDataSource;
    private static Set<String> dataSourceSet = new HashSet<String>();

    public static void setDefaultDataSourceType() {
        contextHolder.set(defaultDataSource);
    }

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSource) {
        return dataSourceSet.contains(dataSource);
    }

    public static void addDataSource(String dataSource) {
        dataSourceSet.add(dataSource);
    }

    public static void setDefaultDataSource(String defaultDataSource) {
        DynamicDataSourceContextHolder.defaultDataSource = defaultDataSource;
    }

    public static boolean isDefaultDataSource(String dataSource) {
        return defaultDataSource == null ? dataSource == null : defaultDataSource.equals(dataSource);
    }

}