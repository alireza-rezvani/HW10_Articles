package ir.maktab32.java.homeworks.hw10articles.features.articlemanagement.usecase;

import ir.maktab32.java.homeworks.hw10articles.entities.db1.Article;

import java.util.List;

public interface FindArticleBasedOnTitleByUserUseCase {
    List<Article> execute();
}
