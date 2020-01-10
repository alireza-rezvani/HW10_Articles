package ir.maktab32.java.homeworks.hw10articles.repositories.db2;

import ir.maktab32.java.homeworks.hw10articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw10articles.entities.db2.Address;
import org.hibernate.Session;

public class AddressRepository extends CrudRepository<Address, Long> {
    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession2();
    }

    private static AddressRepository addressRepository;
    public static AddressRepository getInstance(){
        if (addressRepository == null)
            addressRepository = new AddressRepository();
        return addressRepository;
    }

}
