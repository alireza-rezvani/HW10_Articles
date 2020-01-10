package ir.maktab32.java.homeworks.hw10articles.repositories.db2;

import ir.maktab32.java.homeworks.hw10articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw10articles.entities.db2.Role;
import org.hibernate.Session;

public class RoleRepository extends CrudRepository<Role, Long> {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession2();
    }

    private static RoleRepository roleRepository;
    public static RoleRepository getInstance(){
        if (roleRepository == null)
            roleRepository = new RoleRepository();
        return roleRepository;
    }
}
