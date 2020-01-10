package ir.maktab32.java.homeworks.hw10articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw10articles.entities.db2.User;
import ir.maktab32.java.homeworks.hw10articles.features.usermanagement.usecase.FindAllUsersInfoByAdminUseCase;
import ir.maktab32.java.homeworks.hw10articles.repositories.db2.UserRepository;
import ir.maktab32.java.homeworks.hw10articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw10articles.utilities.UserInfo;

import java.util.List;
import java.util.function.Function;

public class FindAllUsersInfoByAdminUseCaseImpl implements FindAllUsersInfoByAdminUseCase {
    @Override
    public List<UserInfo> execute() {
        List<UserInfo> result;
        if (validation()){
            Function<User,UserInfo> function = user -> new UserInfo(user.getId(),user.getUsername(),user.getAddress().getCity());
            result =  UserRepository.getInstance().findAll(function);
        }
        else {
            System.out.println("\t\u26a0 Loading Users Info Failed!");
            result = null;
        }
        return result;
    }

    private boolean validation(){
        boolean result = true;
        if (!CurrentUserStatus.isAdmin()){
            System.out.println("\t\u26a0 You Are not Signed In as Admin!");
            result = false;
        }
        else if (UserRepository.getInstance().findAll().size() == 0){
            System.out.println("\t\u26a0 There is No User in Database!");
            result = false;
        }
        return result;
    }
}
