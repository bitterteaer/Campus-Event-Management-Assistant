package com.xxx.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * C3P0与Mybatis兼容的数据源工厂类
 * 每个连接池都需要继承 UnpooledDataSourceFactory，
 *然后this.dataSource = new 连接池提供的数据源
 */
public class  C3P0DataSourceFactory extends UnpooledDataSourceFactory {
    public C3P0DataSourceFactory() {
        this.dataSource = new ComboPooledDataSource();//c3p0提供的数据源
    }
}