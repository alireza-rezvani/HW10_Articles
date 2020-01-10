package ir.maktab32.java.homeworks.hw10articles.repositories.db1;

import ir.maktab32.java.homeworks.hw10articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw10articles.entities.db1.Category;
import org.hibernate.Session;

import java.util.List;
import java.util.function.Function;

public class CategoryRepository extends CrudRepository<Category, Long> {
    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession1();
    }

    private static CategoryRepository categoryRepository;
    public static CategoryRepository getInstance(){
        if (categoryRepository == null)
            categoryRepository = new CategoryRepository();
        return categoryRepository;
    }
}
