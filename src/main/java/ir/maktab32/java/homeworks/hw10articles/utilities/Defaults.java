package ir.maktab32.java.homeworks.hw10articles.utilities;

import ir.maktab32.java.homeworks.hw10articles.entities.db2.Address;
import ir.maktab32.java.homeworks.hw10articles.entities.db2.Role;
import ir.maktab32.java.homeworks.hw10articles.entities.db2.User;
import ir.maktab32.java.homeworks.hw10articles.entities.db1.Category;
import ir.maktab32.java.homeworks.hw10articles.entities.db1.Tag;
import ir.maktab32.java.homeworks.hw10articles.repositories.db2.RoleRepository;
import ir.maktab32.java.homeworks.hw10articles.repositories.db2.UserRepository;
import ir.maktab32.java.homeworks.hw10articles.repositories.db1.CategoryRepository;
import ir.maktab32.java.homeworks.hw10articles.repositories.db1.TagRepository;

import java.util.Arrays;

public class Defaults {
    public static void execute(){
        if (RoleRepository.getInstance().findAll().size() == 0){
            RoleRepository.getInstance().save(new Role(null,RoleTitle.ADMIN,null));
            RoleRepository.getInstance().save(new Role(null, RoleTitle.WRITER, null));
        }

        if (!UserRepository.getInstance().isExisting(1l)){
            Address defaultAdminAddress = new Address(null,"Iran","Tehran","Tehran"
                    ,"Iran","1","1111111111");
            User defaultAdmin = new User(null,"admin1","1",
                    "1111111111", "01.01.1988"
                    , Arrays.asList(RoleRepository.getInstance().findById(1l)),defaultAdminAddress);
            UserRepository.getInstance().save(defaultAdmin);
        }

        if (TagRepository.getInstance().findAll().size() == 0){
            TagRepository.getInstance().save(new Tag(null, "tag1", null));
        }

        if (CategoryRepository.getInstance().findAll().size() == 0){
            CategoryRepository.getInstance().save(new Category(null, "category1", "description1"));
        }
    }
}
