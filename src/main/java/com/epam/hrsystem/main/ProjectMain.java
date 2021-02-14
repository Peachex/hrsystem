package com.epam.hrsystem.main;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.VacancyServiceImpl;

import java.util.LinkedHashMap;
import java.util.Map;


public class ProjectMain {
    public static void main(String[] args) throws ServiceException {
        //todo delete main!
        //UserService service = UserServiceImpl.INSTANCE;

        VacancyService service = VacancyServiceImpl.INSTANCE;
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.POSITION, "Test");
        fields.put(RequestParameter.DESCRIPTION, "Join one of Inc 5000’s fastest-growing companies as an IT Recruiter. A top-rated Google Marketing Platform Partner, DELVE is a strategic partner for site-side analytics, campaign management, and advanced marketing science.\n" +
                "\n" +
                "Join and expand our team of analysts, data engineers, data scientists, and media traders that drives client growth through a data-driven mindset that converts digital inefficiency into hard ROI.\n" +
                "\n" +
                "In this role you will collaborate directly with hiring managers to understand who they need, when, why, etc. and prioritize a list of hiring needs, per department, per optimal start date, based on known forecasts of inflowing biz.");
        fields.put(RequestParameter.COUNTRY, "Germany");
        fields.put(RequestParameter.CITY, "Dresden");
        fields.put(RequestParameter.EMPLOYEE_ID, "2");
        //todo delete unnecessary tokens

        //System.out.println(service.createVacancy(fields, 1) + "\n");

        //service.updateVacancyInfo(3, newFields);

       /* System.out.println(service.createVacancy(fields, 1) + "\n");
        List<Vacancy> vacancies = service.findAllVacancies();

        for (Vacancy vacancy : vacancies) {
            System.out.println(vacancy);
        }*/
        //service.deleteVacancy(1);
        //service.restoreVacancy(1);

















        /*fields.put("firstName", "Jack");
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
        }*/

    }
}
