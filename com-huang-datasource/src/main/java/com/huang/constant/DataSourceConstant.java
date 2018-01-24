package com.huang.constant;

/**
 * datasource constant definition
 *
 * @author JeffreyHy
 * @date Created by  2018/1/24 18:30
 */
public class DataSourceConstant {
    public static final String DATASOURCE_NAME = "vDataSource";

    public static final String DATASOURCE_NAME_MASTER = "vDataSourceMaster";

    public static final String DATASOURCE_NAME_SLAVE = "vDataSourceSlave";

    public static final String JDBC_TEMPLATE_NAME = "vJdbcTemplate";

    public static final String TRANSACTION_MANAGER_NAME = "vTransactionManager";

    public static final String SESSION_FACTORY_NAME = "vSqlSessionFactory";

    public static final String MAPPER_PACKAGES = "com.huang.db.mapper";

    public static final String PROFILE_PREFIX_MASTER = "spring.datasource.vdb";

    public static final String PROFILE_PREFIX_SLAVE = "spring.datasource.vdb.slave";

    public static final String MAPPER_LOCATIONS = "mybatis.mapper-locations";
}
