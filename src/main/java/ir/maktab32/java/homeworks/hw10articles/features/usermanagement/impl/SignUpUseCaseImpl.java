package ir.maktab32.java.homeworks.hw10articles.features.usermanagement.impl;


import ir.maktab32.java.homeworks.hw10articles.entities.db2.Address;
import ir.maktab32.java.homeworks.hw10articles.entities.db2.Role;
import ir.maktab32.java.homeworks.hw10articles.entities.db2.User;
import ir.maktab32.java.homeworks.hw10articles.features.usermanagement.usecase.SignUpUseCase;
import ir.maktab32.java.homeworks.hw10articles.repositories.db2.RoleRepository;
import ir.maktab32.java.homeworks.hw10articles.repositories.db2.UserRepository;
import ir.maktab32.java.homeworks.hw10articles.utilities.CurrentUserStatus;
import ir.maktab32.java.homeworks.hw10articles.utilities.IsNumeric;
import ir.maktab32.java.homeworks.hw10articles.utilities.RoleTitle;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SignUpUseCaseImpl implements SignUpUseCase {
    @Override
    public User execute() {
        User result;
        User validateResult = inputAndValidation();
        if (validateResult != null){
            User createdUser = UserRepository.getInstance().save(validateResult);
            System.out.println("\t\t\t\u2705User Created Successfully! User Id: " + createdUser.getId() + " (Password: National Code)");
            result = createdUser;
        }
        else {
            System.out.println("\t\t\t\u26a0 User Creation Failed!");
            result = null;
        }
        return result;
    }

    private User inputAndValidation(){
        Scanner scanner = new Scanner(System.in);

        User result = null;

        if (CurrentUserStatus.isSignedIn())
            System.out.println("\t\u26a0 Another User is Signed In! Sign Out First!");
        else {
            String username = inputUsername();
            String nationalCode = inputNationalCode();
            System.out.print("\t\u29bf Birth Date: ");
            String birthDate = scanner.nextLine();
            Address address = inputAddress();
            Role writerRole = null;
            List<Role> allRoles = RoleRepository.getInstance().findAll();
            for (Role i : allRoles){
                if (i.getTitle().equals(RoleTitle.WRITER)){
                    writerRole = i;
                    break;
                }
            }
            if (writerRole == null)
                System.out.println("\t\t\u26a0 Add Writer Role to Database As Admin!");

            if (username != null && nationalCode != null && writerRole != null && address != null){
                result = new User(null, username, nationalCode, nationalCode, birthDate, Arrays.asList(writerRole), address);
            }
        }
        return result;
    }
    private String inputUsername(){
        String username = null;

        Scanner scanner = new Scanner(System.in);
        List<User> allUsers = UserRepository.getInstance().findAll();
        while (username == null){
            System.out.print("\t\u29bf Username: ");
            username = scanner.nextLine();
            if (username.isEmpty()){
                System.out.println("\t\t\u26a0 Username Can't Be Empty!");
                username = null;
            }
            else {
                if (allUsers.size() > 0) {
                    for (User i : allUsers) {
                        if (i.getUsername().equals(username)) {
                            System.out.println("\t\t\u26a0 This Username Already Exists!");
                            username = null;
                            break;
                        }
                    }
                }
            }
        }
        return username;
    }
    private String inputNationalCode(){
        Scanner scanner = new Scanner(System.in);

        String nationalCode = null;
        while (nationalCode == null){
            System.out.print("\t\u29bf National Code: ");
            nationalCode = scanner.nextLine();

            if ((!IsNumeric.execute(nationalCode)) || nationalCode.length() != 10){
                System.out.println("\t\t\u26a0 Invalid National Code!");
                nationalCode = null;
            }
        }
        return nationalCode;
    }

    private Address inputAddress(){
        Scanner scanner = new Scanner(System.in);
        Address address = null;
        System.out.println("\t\u29bf Address Information:");
        System.out.print("\t\t\u29bf Country: ");
        String country = scanner.nextLine();
        System.out.print("\t\t\u29bf Province: ");
        String province = scanner.nextLine();
        System.out.print("\t\t\u29bf City: ");
        String city = scanner.nextLine();
        System.out.print("\t\t\u29bf Street: ");
        String street = scanner.nextLine();
        System.out.print("\t\t\u29bf Number: ");
        String number = scanner.nextLine();
        System.out.print("\t\t\u29bf ZipCode: ");
        String zipCode = scanner.nextLine();

        if (country.isEmpty() || province.isEmpty() || city.isEmpty() || street.isEmpty() || number.isEmpty() || zipCode.isEmpty())
            System.out.println("\t\t\t\u26a0 Fill All The Fields!");
        else
            address = new Address(null, country,province,city,street,number, zipCode);

        return address;
    }
}
