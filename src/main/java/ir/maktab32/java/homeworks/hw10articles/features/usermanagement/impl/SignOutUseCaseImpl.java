package ir.maktab32.java.homeworks.hw10articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw10articles.features.usermanagement.usecase.SignOutUseCase;
import ir.maktab32.java.homeworks.hw10articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw10articles.utilities.CurrentUserStatus;

public class SignOutUseCaseImpl implements SignOutUseCase {
    @Override
    public boolean execute() {
        boolean result = false;
        if (signOutValidation()){
            AuthenticationService.getInstance().setSignedInUser(null);
            result = true;
            System.out.println("\t\u2705 Sign Out Successful!");
        }
        else
            System.out.println("\t\u26a0 Sign Out Failed!");
        return result;
    }

    private boolean signOutValidation(){
        if (!CurrentUserStatus.isSignedIn()){
            System.out.println("\t\u26a0 You Are not Signed In!");
            return false;
        }
        else
            return true;
    }
}
