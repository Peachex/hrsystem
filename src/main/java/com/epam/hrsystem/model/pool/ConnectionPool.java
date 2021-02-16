package com.epam.hrsystem.model.pool;

import com.epam.hrsystem.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    POOL;
    private final Logger logger = LogManager.getLogger();
    public static final int DEFAULT_POOL_SIZE = 10;
    public static final int FATAL_CONNECTION_ERROR_NUMBER = 5;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenConnections;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>();
        givenConnections = new ArrayDeque<>();
        int errorCounter = 0;
        int poolSize;
        try {
            poolSize = Integer.parseInt(ConnectionCreator.getPoolSize());
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't read pool size, default size = " + DEFAULT_POOL_SIZE + " will be used: " + e);
            poolSize = DEFAULT_POOL_SIZE;
        }
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection hasn't been created" + e);
                errorCounter++;
            }
        }
        if (errorCounter >= FATAL_CONNECTION_ERROR_NUMBER) {
            logger.log(Level.FATAL, errorCounter + " connections haven't been created");
            throw new RuntimeException(errorCounter + " connections haven't been created");
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Couldn't take connection: " + e);
            throw new ConnectionPoolException(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.ERROR, "Couldn't release connection");
            throw new RuntimeException("Couldn't release connection");
        }
    }

    public void destroyPool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                ProxyConnection proxyConnection = freeConnections.take();
                proxyConnection.reallyClose();
            }
        } catch (InterruptedException | SQLException e) {
            logger.log(Level.ERROR, "Couldn't destroy connection pool: " + e);
            throw new ConnectionPoolException(e);
        } finally {
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() throws ConnectionPoolException {
        try {
            while (DriverManager.getDrivers().hasMoreElements()) {
                Driver driver = DriverManager.getDrivers().nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Couldn't deregister drivers: " + e);
            throw new ConnectionPoolException(e);
        }
    }
}
