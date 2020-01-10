package ir.maktab32.java.homeworks.hw10articles.repositories.db2;

import ir.maktab32.java.homeworks.hw10articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw10articles.entities.db2.User;
import org.hibernate.Session;

public class UserRepository extends CrudRepository<User, Long> {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession2();
    }

    private static UserRepository userRepository;
    public static UserRepository getInstance(){
        if (userRepository == null)
            userRepository = new UserRepository();
        return userRepository;
    }
}
