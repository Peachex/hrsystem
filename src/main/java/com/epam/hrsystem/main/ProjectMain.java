package com.epam.hrsystem.main;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.UserServiceImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ProjectMain {
    public static void main(String[] args) throws ServiceException {
        //todo delete main!
        UserService service = UserServiceImpl.INSTANCE;
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("firstName", "Jack");
        fields.put("lastName", "Olly");
        fields.put("dayOfBirth", "2005-04-02");
        fields.put("phoneNumber", "+375-33-196-98-53");
        fields.put("email", "goodOlly_2005@tut.by");
        fields.put("password", "password");
        fields.put("passwordRepeat", "password");

        Map<String, String> newUserInfo = new LinkedHashMap<>();
        newUserInfo.put("firstName", "Peter");
        newUserInfo.put("lastName", "Petrov");
        newUserInfo.put("email", "petrov-2005@tut.by");

        System.out.println(service.updateProfile(3, newUserInfo) + "\n");

        List<User> users = service.findAllUsers();


        for (User user : users) {
            System.out.println(user);
        }

    }
}
