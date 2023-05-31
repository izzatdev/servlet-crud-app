package com.example.demo1.config;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.example.demo1.utils.ConnectionsUtils.*;

@WebListener
public class LiquibaseInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Get the database connection
            Connection connection = DriverManager.getConnection(datasourcePath + datasourceName, username, password);

            // Create a Liquibase database instance
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            // Initialize Liquibase
            Liquibase liquibase = new Liquibase("db/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(""); // Perform database updates

            // Close the database connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Cleanup resources if needed
    }
}