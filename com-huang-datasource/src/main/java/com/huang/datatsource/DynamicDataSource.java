package com.huang.datatsource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * custom implementation by {@link org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource}
 *
 * @author JeffreyHy
 * @date Created by  2018/1/24 18:30
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}