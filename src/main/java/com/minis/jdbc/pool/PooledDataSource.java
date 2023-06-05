package com.minis.jdbc.pool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/5 16:03 
 */

public class PooledDataSource implements DataSource {
    private List<PooledConnection> connections = null;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        try {
            Class.forName(this.driverClassName);
        }
        catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Could not load JDBC driver class [" + driverClassName + "]", ex);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int initialSize = 2;
    private Properties connectionProperties;

    private void initPool() throws SQLException {
        this.connections = new ArrayList<>(initialSize);
        for(int i = 0; i < initialSize; i++){
            Connection connect = DriverManager.getConnection(url, username, password);
            PooledConnection pooledConnection = new PooledConnection(connect, false);
            this.connections.add(pooledConnection);
        }
    }


    private PooledConnection getAvailableConnection() throws SQLException{
        if(this.connections == null){
            initPool();
        }
        for(PooledConnection pooledConnection : this.connections){
            if (!pooledConnection.isActive()){
                pooledConnection.setActive(true);
                return pooledConnection;
            }
        }

        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        PooledConnection pooledConnection= getAvailableConnection();
        while(pooledConnection == null){
            pooledConnection = getAvailableConnection();
            if(pooledConnection == null){
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return pooledConnection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
