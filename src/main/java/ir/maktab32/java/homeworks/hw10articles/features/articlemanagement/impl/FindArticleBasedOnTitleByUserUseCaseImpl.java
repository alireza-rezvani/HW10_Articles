package ir.maktab32.java.homeworks.hw10articles.features.articlemanagement.impl;

import ir.maktab32.java.homeworks.hw10articles.entities.db1.Article;
import ir.maktab32.java.homeworks.hw10articles.features.articlemanagement.usecase.FindArticleBasedOnTitleByUserUseCase;
import ir.maktab32.java.homeworks.hw10articles.repositories.db1.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class FindArticleBasedOnTitleByUserUseCaseImpl implements FindArticleBasedOnTitleByUserUseCase {
    @Override
    public List<Article> execute() {
        List<Article> result;
        String validatedTitle = inputAndValidation();
        if (validatedTitle != null){
            result = ArticleRepository.getInstance().findAll((Predicate<Article>)  article -> article.getTitle().contains(validatedTitle));

            if (result.size() != 0)
                System.out.println("\t\t\u2705 Articles Found Successfully!");
            else {
                System.out.println("\t\t\u26a0 No Matches Found!");
                result = null;
            }
        }
        else {
            System.out.println("\t\t\u26a0 Loading Articles Failed!");
            result = null;
        }
        return result;
    }

    private String inputAndValidation(){
        Scanner scanner = new Scanner(System.in);
        String result;

        System.out.print("\t\u29bf Title: ");
        result = scanner.nextLine();

        //nothing to check
        return result;
    }
}
