package ir.maktab32.java.homeworks.hw10articles.config.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory1;
    private static SessionFactory sessionFactory2;

    private static Session session1;
    private static Session session2;

    static {
        sessionFactory1 = new Configuration().configure("hibernate.db1.cfg.xml").buildSessionFactory();
        sessionFactory2 = new Configuration().configure("hibernate.db2.cfg.xml").buildSessionFactory();
        session1 = sessionFactory1.openSession();
        session2 = sessionFactory2.openSession();
    }

    public static Session getSession1() {
        return session1;
    }

    public static Session getSession2() {
        return session2;
    }
}
