package ir.maktab32.java.homeworks.hw10articles.repositories.db1;

import ir.maktab32.java.homeworks.hw10articles.config.hibernate.HibernateUtil;
import ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw10articles.entities.db1.Article;
import org.hibernate.Session;

import java.util.List;
import java.util.function.Function;

public class ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession1();
    }

    private static ArticleRepository articleRepository;
    public static ArticleRepository getInstance(){
        if (articleRepository == null)
            articleRepository = new ArticleRepository();
        return articleRepository;
    }
}
