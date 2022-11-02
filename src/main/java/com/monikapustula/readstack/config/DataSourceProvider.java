package com.monikapustula.readstack.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceProvider {
    private static DataSource dataSource;

    private DataSourceProvider() {
    }

    public static DataSource getDataSource() throws NamingException {
        if (dataSource == null) {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env/");
            dataSource = (DataSource) envContext.lookup("jdbc/readstack");
        }
        return dataSource;
    }
}
