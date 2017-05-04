package org.smart4j.framework.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/4/2.
 */
public class DatabaseHelper {
    public static ThreadLocal<Connection> CONNECTION_HOLDER=new ThreadLocal<Connection>();
    public static Connection conn=null;

    public static Connection getConnection(){
        Connection conn=CONNECTION_HOLDER.get();
        try{
            if(conn==null){
                Class.forName(ConfigHelper.getJdbcDriver());
                conn= DriverManager.getConnection(ConfigHelper.getJdbcUrl(),ConfigHelper.getJdbcUsername(),ConfigHelper.getJdbcPassword());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CONNECTION_HOLDER.set(conn);
        }
        return conn;
    }
    //
    public static DataSource getDataSource(){
        Properties prop=new Properties();
        prop.setProperty("url",ConfigHelper.getJdbcUrl());
        prop.setProperty("driverClassName",ConfigHelper.getJdbcDriver());
        prop.setProperty("username",ConfigHelper.getJdbcUsername());
        prop.setProperty("password",ConfigHelper.getJdbcPassword());
        try {
            DataSource ds= BasicDataSourceFactory.createDataSource(prop);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //开启事物
    public static void beginTransaction(){
        Connection conn=getConnection();
        if(conn!=null){
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
    }
    //提交事物
    public static void commitTransaction(){
        Connection conn=getConnection();
        if(conn!=null){
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    public static void rollbackTransaction(){
        Connection conn=getConnection();
        if(conn!=null){
            try {
                conn.rollback();
                conn.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
}
