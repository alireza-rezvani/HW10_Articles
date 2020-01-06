package ir.maktab32.java.homeworks.hw10articles.repositories;

import ir.maktab32.java.homeworks.hw10articles.config.hibernate.HibernateUtilForTest;
import ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw10articles.entities.TestEntity;
import org.hibernate.Session;

public class TestEntityRepository extends CrudRepository<TestEntity, Long> {
    @Override
    protected Class<TestEntity> getEntityClass() {
        return TestEntity.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtilForTest.getSessionH2();
    }

    private static TestEntityRepository testEntityRepository;
    public static TestEntityRepository getInstance(){
        if (testEntityRepository == null)
            testEntityRepository = new TestEntityRepository();
        return testEntityRepository;
    }
}
