package com.epam.hrsystem.validator;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class UserValidatorTest {
    @DataProvider
    public Object[][] userRolesData() {
        return new Object[][]{
                {"guest", true},
                {"ADMIN", true},
                {"emplOYEE", true},
                {"AppliCANT", true},
                {"client", false},
                {"manager", false},
                {null, false},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] namesData() {
        return new Object[][]{
                {"Aleksey", true},
                {"ALICE", true},
                {"AL", false},
                {"John24", false},
                {null, false},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] phoneNumbersData() {
        return new Object[][]{
                {"+375291112233", true},
                {"+375-29-111-22-33", true},
                {"(8029)-111-22-33", true},
                {"phone number", false},
                {null, false},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] emailsData() {
        return new Object[][]{
                {"example@gmail.com", true},
                {"examp1e22@mail.ru", true},
                {"12EXAMPLE123@yandex.by", true},
                {"bad@", false},
                {null, false},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] photoNamesData() {
        return new Object[][]{
                {"ada-24dsfawf-3gse-y4", true},
                {"2424125412512521", true},
                {"image", true},
                {"more than 50 characters aokfowkfao3infjaufhnurb3fubyefewfiwg" +
                        "dawdoawdaowdmaodmaigfnjawngiasnvsnganwginaowngiej i42" +
                        "iawfnwaufnanifhaunhdiawsnbhdiawbniahnawnhe7q23yhrwudd" +
                        "dwadwaofjawifjw89qj2hfuhfqh9wfu9qh7f8hrehw", false},
                {null, false},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] passwordsData() {
        return new Object[][]{
                {"password", "password", true},
                {"хорошийgood2425Password_13", "хорошийgood2425Password_13", true},
                {"onePassword", "otherPassword", false},
                {"password", null, false},
                {null, "password", false},
                {null, null, false},
                {"", "password", false},
                {"password", "", false},
                {"", null, false},
                {null, "", false},
        };
    }

    @DataProvider
    public Object[][] fieldsForRegisterFormData() {
        HashMap<String, String> correctFields = new HashMap<>();
        correctFields.put(RequestParameter.FIRST_NAME, "Alice");
        correctFields.put(RequestParameter.LAST_NAME, "Green");
        correctFields.put(RequestParameter.DATE_OF_BIRTH, "2000-06-24");
        correctFields.put(RequestParameter.PHONE_NUMBER, "+375391112223");
        correctFields.put(RequestParameter.EMAIL, "alice@gmail.com");
        correctFields.put(RequestParameter.PASSWORD, "password");
        correctFields.put(RequestParameter.REPEATED_PASSWORD, "password");

        HashMap<String, String> wrongFields = new HashMap<>();
        wrongFields.put(RequestParameter.FIRST_NAME, "125252");
        wrongFields.put(RequestParameter.LAST_NAME, "Green");
        wrongFields.put(RequestParameter.DATE_OF_BIRTH, "200000-062-24");
        wrongFields.put(RequestParameter.PHONE_NUMBER, "3dwa75gw391112223");
        wrongFields.put(RequestParameter.EMAIL, "alice@gmail.com");
        wrongFields.put(RequestParameter.PASSWORD, "");
        wrongFields.put(RequestParameter.REPEATED_PASSWORD, null);

        return new Object[][]{
                {correctFields, true},
                {wrongFields, false},
        };
    }

    @Test(dataProvider = "userRolesData")
    public void isUserRoleValidTest(String role, boolean expected) {
        boolean actual = UserValidator.isUserRoleValid(role);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "namesData")
    public void isNameValidTest(String name, boolean expected) {
        boolean actual = UserValidator.isNameValid(name);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "phoneNumbersData")
    public void isPhoneNumberValidTest(String phoneNumber, boolean expected) {
        boolean actual = UserValidator.isPhoneNumberValid(phoneNumber);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "emailsData")
    public void isEmailValidTest(String email, boolean expected) {
        boolean actual = UserValidator.isEmailValid(email);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "photoNamesData")
    public void isPhotoNameValidTest(String photoName, boolean expected) {
        boolean actual = UserValidator.isPhotoNameValid(photoName);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "passwordsData")
    public void isRepeatPasswordValidTest(String password, String repeatPassword, boolean expected) {
        boolean actual = UserValidator.isRepeatPasswordValid(password, repeatPassword);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "fieldsForRegisterFormData")
    public void isRegisterFormValidTest(Map<String, String> fields, boolean expected) {
        boolean actual = UserValidator.isRegisterFormValid(fields);
        assertEquals(actual, expected);
    }
}
