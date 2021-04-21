package com.epam.hrsystem.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class used to create Connection objects.
 *
 * @author Aleksey Klevitov
 */
final class ConnectionCreator {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static final String DATABASE_PROPERTY_FILE_PATH = "/property/database.properties";
    private static final String DATABASE_DRIVER_FIELD = "db.driver";
    private static final String DATABASE_URL_FIELD = "db.url";
    private static final String DATABASE_POOL_SIZE_FIELD = "pool.size";
    private static final String POOL_SIZE;

    static {
        try {
            properties.load(ConnectionCreator.class.getResourceAsStream(DATABASE_PROPERTY_FILE_PATH));
            String driverName = (String) properties.get(DATABASE_DRIVER_FIELD);
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException e) {
            logger.log(Level.ERROR, "Couldn't load properties: " + e);
        }
        DATABASE_URL = (String) properties.get(DATABASE_URL_FIELD);
        POOL_SIZE = (String) properties.get(DATABASE_POOL_SIZE_FIELD);
    }

    private ConnectionCreator() {
    }

    /**
     * Getter method of pool size.
     *
     * @return String object of pool's size.
     */
    static String getPoolSize() {
        return POOL_SIZE;
    }

    /**
     * Creates a Connection object.
     *
     * @return Connection object.
     * @throws SQLException if a database access error occurs or the url is null.
     */
    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}
