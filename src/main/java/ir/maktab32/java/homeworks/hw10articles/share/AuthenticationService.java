package ir.maktab32.java.homeworks.hw10articles.share;

import ir.maktab32.java.homeworks.hw10articles.entities.db2.User;

public class AuthenticationService {

    private AuthenticationService(){}

    private User signedInUser;

    private static AuthenticationService authenticationService = null;
    public static AuthenticationService getInstance(){
        if (authenticationService == null)
            authenticationService = new AuthenticationService();
        return authenticationService;
    }

    public User getSignedInUser(){return signedInUser;}
    public void setSignedInUser(User user){this.signedInUser = user;}

}
