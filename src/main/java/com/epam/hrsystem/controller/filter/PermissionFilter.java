package com.epam.hrsystem.controller.filter;

import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.attribute.UrlPattern;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandType;
import com.epam.hrsystem.controller.command.CommandProvider;
import com.epam.hrsystem.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PermissionFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final Map<UserRole, List<CommandType>> permissionCommands = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        List<CommandType> sameCommands = new ArrayList<>();
        sameCommands.add(CommandType.CHANGE_LANGUAGE);
        sameCommands.add(CommandType.TO_VACANCIES);
        sameCommands.add(CommandType.TO_VACANCY_INFO);
        sameCommands.add(CommandType.FIND_VACANCIES_BY_KEY_WORD);
        sameCommands.add(CommandType.SORT_VACANCIES_BY_DATE);

        List<CommandType> guestCommands = new ArrayList<>(sameCommands);
        guestCommands.add(CommandType.REGISTER);
        guestCommands.add(CommandType.LOGIN);

        List<CommandType> applicantCommands = new ArrayList<>(sameCommands);
        applicantCommands.add(CommandType.LOGOUT);
        applicantCommands.add(CommandType.CREATE_APPLICANT_REQUEST);
        applicantCommands.add(CommandType.TO_USER_PROFILE);
        applicantCommands.add(CommandType.EDIT_USER_PROFILE);
        applicantCommands.add(CommandType.TO_APPLICANT_REQUESTS);
        applicantCommands.add(CommandType.PROVIDE_IMAGE);

        List<CommandType> employeeCommands = new ArrayList<>(sameCommands);
        employeeCommands.add(CommandType.LOGOUT);
        employeeCommands.add(CommandType.CREATE_VACANCY);
        employeeCommands.add(CommandType.DELETE_VACANCY);
        employeeCommands.add(CommandType.RESTORE_VACANCY);
        employeeCommands.add(CommandType.TO_EMPLOYEE_VACANCIES);
        employeeCommands.add(CommandType.TO_EMPLOYEE_VACANCY_INFO);
        employeeCommands.add(CommandType.SEE_ACTIVE_EMPLOYEE_VACANCIES);
        employeeCommands.add(CommandType.SEE_DELETED_EMPLOYEE_VACANCIES);
        employeeCommands.add(CommandType.SEE_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS);
        employeeCommands.add(CommandType.SEE_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS);
        employeeCommands.add(CommandType.SEE_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS);
        employeeCommands.add(CommandType.EDIT_VACANCY_INFO);
        employeeCommands.add(CommandType.TO_USER_PROFILE);
        employeeCommands.add(CommandType.EDIT_USER_PROFILE);
        employeeCommands.add(CommandType.TO_EMPLOYEE_APPLICANT_REQUEST);
        employeeCommands.add(CommandType.PROVIDE_IMAGE);

        List<CommandType> adminCommands = new ArrayList<>(sameCommands);
        adminCommands.add(CommandType.LOGOUT);
        adminCommands.add(CommandType.CREATE_VACANCY);
        adminCommands.add(CommandType.SEE_ALL_VACANCIES);
        adminCommands.add(CommandType.DELETE_VACANCY);
        adminCommands.add(CommandType.RESTORE_VACANCY);
        adminCommands.add(CommandType.TO_USER_PROFILE);
        adminCommands.add(CommandType.EDIT_USER_PROFILE);
        adminCommands.add(CommandType.PROVIDE_IMAGE);

        permissionCommands.put(UserRole.GUEST, guestCommands);
        permissionCommands.put(UserRole.APPLICANT, applicantCommands);
        permissionCommands.put(UserRole.EMPLOYEE, employeeCommands);
        permissionCommands.put(UserRole.ADMIN, adminCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(SessionAttribute.CURRENT_ROLE);
        Optional<ActionCommand> optionalCommand = CommandProvider.defineCommand(request);
        if (!optionalCommand.isPresent()) {
            filterChain.doFilter(request, response);
            return;
        }
        List<CommandType> commands = permissionCommands.get(role);
        CommandType command = CommandProvider.defineCommandType(request).get();
        if (commands == null || !commands.contains(command)) {
            logger.log(Level.ERROR, "User hasn't got permission to execute command = " + command);
            RequestDispatcher dispatcher = request.getRequestDispatcher(UrlPattern.HOME);
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
