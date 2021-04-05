package com.epam.hrsystem.model.pool;

import com.epam.hrsystem.controller.Controller;
import com.epam.hrsystem.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class represents a connection pool.
 *
 * @author Aleksey Klevitov
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final int FATAL_CONNECTION_ERROR_NUMBER = 5;
    private final BlockingQueue<ProxyConnection> freeConnections;

    /**
     * Enumeration with a single object in it (thread-safe singleton) used to connection pool manage.
     */
    public enum ConnectionPoolHolder {
        /**
         * Represents a singleton pattern realization.
         */
        POOL;
        private final ConnectionPool connectionPool = new ConnectionPool();

        /**
         * Getter method of connection pool.
         *
         * @return ConnectionPool object.
         */
        public ConnectionPool getConnectionPool() {
            return connectionPool;
        }

        /**
         * Starts lazy initialization of connection pool.
         */
        public void init() {
        }
    }

    private ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>();
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

    /**
     * Takes connection from connection pool.
     *
     * @return Connection object.
     * @throws ConnectionPoolException if InterruptedException was thrown while processing.
     */
    public Connection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Couldn't take connection: " + e);
            throw new ConnectionPoolException(e);
        }
        return connection;
    }

    /**
     * Puts a Connection object back in the pool.
     *
     * @param connection Connection object that must be an instance of ProxyConnection.
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.ERROR, "Couldn't release connection");
        }
    }

    /**
     * Destroys a connection pool. Should be called before finishing the program.
     *
     * @param object Object object. The object must be an object from Controller class to destroy the connection pool.
     * @throws ConnectionPoolException if InterruptedException or SQLException were thrown while processing.
     */
    public void destroyPool(Object object) throws ConnectionPoolException {
        if (object.getClass() == Controller.class) {
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
        } else {
            logger.log(Level.WARN, "Couldn't destroy pool cause method was called not from controller");
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
