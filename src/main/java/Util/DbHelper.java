package Util;
import User.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DbHelper {
    private static DbHelper dbHelper;

    public static DbHelper getInstance(){

        if(dbHelper==null){
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }

    private DbHelper() {
    }

    private static Configuration getConfiguration()  {
        Properties properties = new Properties();
        Configuration configuration = new Configuration();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(in);

        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", properties.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class",  properties.getProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url",  properties.getProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username",  properties.getProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password",  properties.getProperty("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql",  properties.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto",  properties.getProperty("hibernate.hbm2ddl.auto"));
        return configuration;
    } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }

        public  org.hibernate.SessionFactory createSessionFactory(){
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public  Connection getJDBCConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(in);

        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            connection = DriverManager.getConnection( properties.getProperty("jdbc.connection.url"),
                    properties.getProperty("jdbc.connection.user"),
                    properties.getProperty("jdbc.connection.password"));
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

