package ir.maktab32.java.homeworks.hw10articles.utilities;

import ir.maktab32.java.homeworks.hw10articles.entities.db2.Role;
import ir.maktab32.java.homeworks.hw10articles.entities.db2.User;
import ir.maktab32.java.homeworks.hw10articles.share.AuthenticationService;

public class CurrentUserStatus {
    public static boolean isSignedIn(){
        boolean result = false;
        if (AuthenticationService.getInstance().getSignedInUser() != null)
            result = true;
        return result;
    }
    public static boolean isWriter(){
        boolean result = false;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null)
            result = false;
        else {
            for (Role i : currentUser.getRoles()){
                if (i.getTitle().equals(RoleTitle.WRITER)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    public static boolean isAdmin(){
        boolean result = false;
        User currentUser = AuthenticationService.getInstance().getSignedInUser();
        if (currentUser == null)
            result = false;
        else {
            for (Role i : currentUser.getRoles()){
                if (i.getTitle().equals(RoleTitle.ADMIN)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
