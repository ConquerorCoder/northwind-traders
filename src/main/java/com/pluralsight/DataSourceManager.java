package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceManager {

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup24");

        dataSource.setInitialSize(5);
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
}