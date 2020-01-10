package ir.maktab32.java.homeworks.hw10articles.repositories.db1;

import ir.maktab32.java.homeworks.hw10articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw10articles.entities.db1.Tag;
import org.hibernate.Session;

import java.util.List;
import java.util.function.Function;

public class TagRepository extends CrudRepository<Tag, Long> {
    @Override
    protected Class<Tag> getEntityClass() {
        return Tag.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession1();
    }

    private static TagRepository tagRepository;
    public static TagRepository getInstance(){
        if (tagRepository == null)
            tagRepository = new TagRepository();
        return tagRepository;
    }
}
