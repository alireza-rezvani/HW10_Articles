package ir.maktab32.java.homeworks.hw10articles.features.dashboard.impl;


import ir.maktab32.java.homeworks.hw10articles.entities.db1.Article;
import ir.maktab32.java.homeworks.hw10articles.features.dashboard.usecase.GetDashboardByAdminUseCase;
import ir.maktab32.java.homeworks.hw10articles.repositories.db2.UserRepository;
import ir.maktab32.java.homeworks.hw10articles.repositories.db1.ArticleRepository;
import ir.maktab32.java.homeworks.hw10articles.utilities.CurrentUserStatus;

import java.util.List;

public class GetDashboardByAdminUseCaseImpl implements GetDashboardByAdminUseCase {
    @Override
    public String execute() {
        String result;
        if (validation()){
            result = "\t\u2705 Number of Users: " + UserRepository.getInstance().findAll().size();
            result += "\n\t\u2705 Number of Articles: " + ArticleRepository.getInstance().findAll().size();
            int publishedCounter = 0;
            int unPublishedCounter = 0;
            List<Article> allArticles = ArticleRepository.getInstance().findAll();
            if (allArticles.size() != 0) {
                for (Article i : allArticles) {
                    if (i.getIsPublished() == true)
                        publishedCounter++;
                    else
                        unPublishedCounter++;
                }
            }
            result += "\n\t\u2705 Number of Published Articles: " + publishedCounter;
            result += "\n\t\u2705 Number of UnPublished Articles: " + unPublishedCounter;
        }
        else {
         result = "\t\t\u26a0 Loading Dashboard Failed!";
        }
        return result;
    }

    private boolean validation(){
        boolean result = true;
        if (!CurrentUserStatus.isAdmin()){
            System.out.println("\t\u26a0 You Are not Signed In as Admin!");
            result = false;
        }
        return result;
    }
}
