package DAO;
import Util.DbHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractDaoFactory {
    private static AbstractDaoFactory instance;
    private DbHelper dbHelper = DbHelper.getInstance();

    private AbstractDaoFactory() {
    }

    public static AbstractDaoFactory getInstance() {
        if (instance == null) {
            instance = new AbstractDaoFactory();
        }
        return instance;
    }


    public DAO getUserDao() throws IllegalArgumentException, IOException {
        Properties properties = new Properties();
        DAO dao = null;
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(in);
            String nameOfDao = properties.getProperty("MethodToConnectDB");
            if (nameOfDao.contains("JDBC")) {
                dao = JDBCDao.getInstance(dbHelper.getJDBCConnection());
            } else if (nameOfDao.contains("Hibernate")) {
                dao =  HibernateDao.getInstance(dbHelper.createSessionFactory());
            } else {
                throw new IllegalArgumentException("Required configuration parameter 'daotype' is not provided'");
            }
        }
        return dao;
    }

}