package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static SessionFactory sessionFactory;

    private Util() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USER);
                properties.put(Environment.PASS, PSW);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                builder.applySettings(properties);
                StandardServiceRegistry registry = builder.build();
                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(User.class);

                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (sessionFactory != null) {
                    sessionFactory.close();
                }
            }
        }
        return sessionFactory;
    }

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/katabd";
    private static final String USER = "root";
    private static final String PSW = "1234";


    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PSW);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return connection;


    }


}

