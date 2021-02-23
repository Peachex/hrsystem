package com.epam.hrsystem.controller.filter;

import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.attribute.UrlPattern;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandEnum;
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
    private static final Map<UserRole, List<CommandEnum>> permissionCommands = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        List<CommandEnum> sameCommands = new ArrayList<>();
        sameCommands.add(CommandEnum.CHANGE_LANGUAGE);
        sameCommands.add(CommandEnum.TO_VACANCIES);
        sameCommands.add(CommandEnum.TO_VACANCY_INFO);
        sameCommands.add(CommandEnum.FIND_VACANCIES_BY_KEY_WORD);
        sameCommands.add(CommandEnum.SORT_VACANCIES_BY_DATE);

        List<CommandEnum> guestCommands = new ArrayList<>(sameCommands);
        guestCommands.add(CommandEnum.REGISTER);
        guestCommands.add(CommandEnum.LOGIN);

        List<CommandEnum> applicantCommands = new ArrayList<>(sameCommands);
        applicantCommands.add(CommandEnum.LOGOUT);
        applicantCommands.add(CommandEnum.CREATE_APPLICANT_REQUEST);

        List<CommandEnum> employeeCommands = new ArrayList<>(sameCommands);
        employeeCommands.add(CommandEnum.LOGOUT);
        employeeCommands.add(CommandEnum.CREATE_VACANCY);
        employeeCommands.add(CommandEnum.DELETE_VACANCY);
        employeeCommands.add(CommandEnum.RESTORE_VACANCY);
        employeeCommands.add(CommandEnum.TO_EMPLOYEE_VACANCIES);
        employeeCommands.add(CommandEnum.TO_EMPLOYEE_VACANCY_INFO);
        employeeCommands.add(CommandEnum.SEE_ACTIVE_EMPLOYEE_VACANCIES);
        employeeCommands.add(CommandEnum.SEE_DELETED_EMPLOYEE_VACANCIES);
        employeeCommands.add(CommandEnum.SEE_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS);
        employeeCommands.add(CommandEnum.SEE_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS);
        employeeCommands.add(CommandEnum.SEE_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS);
        employeeCommands.add(CommandEnum.EDIT_VACANCY_INFO);

        List<CommandEnum> adminCommands = new ArrayList<>(sameCommands);
        adminCommands.add(CommandEnum.LOGOUT);
        adminCommands.add(CommandEnum.CREATE_VACANCY);
        adminCommands.add(CommandEnum.SEE_ALL_VACANCIES);
        adminCommands.add(CommandEnum.DELETE_VACANCY);
        adminCommands.add(CommandEnum.RESTORE_VACANCY);

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
        List<CommandEnum> commands = permissionCommands.get(role);
        CommandEnum command = CommandProvider.defineCommandType(request).get();
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
