package DBService;

import com.mysql.jdbc.Connection;
import main.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;

/**
 * Created by matt on 03.01.2016.
 */
public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration config = getMySQLConfiguration();
        sessionFactory = createSessionFactory(config);
    }
    private Configuration getMySQLConfiguration() {
        Configuration config = new Configuration();
        config.addAnnotatedClass(UserProfile.class);

        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/jtest");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "root");
        config.setProperty("hibernate.show_sql", hibernate_show_sql);
        config.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return config;
    }

    public UserProfile getUser(long id) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            UserProfile dataset = dao.get(id);
            session.close();
            return dataset;
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public long addUser(String login){
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        UsersDAO dao = new UsersDAO(session);
        long id = dao.insertUser(login);
        trx.commit();
        session.close();
        return id;
    }
    public long addUser(String login, String pass){
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        UsersDAO dao = new UsersDAO(session);
        long id = dao.insertUser(login, pass);
        trx.commit();
        session.close();
        return id;
    }

    public boolean userExist(String login){
        Session session = sessionFactory.openSession();
        UsersDAO dao = new UsersDAO(session);
        UserProfile usr = dao.get(login);
        if (usr != null) {
            return true;
        }
        return false;
    }

    public void printConnectionInfo(){
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            java.sql.Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory createSessionFactory(Configuration configuration){
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
