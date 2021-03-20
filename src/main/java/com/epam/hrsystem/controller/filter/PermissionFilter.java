package com.epam.hrsystem.controller.filter;

import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.attribute.ServletAttribute;
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
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PermissionFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final Map<UserRole, EnumSet<CommandType>> permissionCommands = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        EnumSet<CommandType> sameCommands = EnumSet.of(CommandType.CHANGE_LANGUAGE, CommandType.TO_VACANCIES,
                CommandType.TO_VACANCY_INFO, CommandType.FIND_VACANCIES_BY_KEY_WORD, CommandType.SORT_VACANCIES_BY_DATE);

        EnumSet<CommandType> guestCommands = EnumSet.of(CommandType.REGISTER, CommandType.LOGIN);
        guestCommands.addAll(sameCommands);

        EnumSet<CommandType> applicantCommands = EnumSet.of(CommandType.LOGOUT, CommandType.CREATE_APPLICANT_REQUEST,
                CommandType.TO_USER_PROFILE, CommandType.EDIT_USER_PROFILE, CommandType.TO_APPLICANT_REQUESTS,
                CommandType.PROVIDE_IMAGE, CommandType.CHANGE_USER_PASSWORD, CommandType.DELETE_USER_ACCOUNT);
        applicantCommands.addAll(sameCommands);

        EnumSet<CommandType> employeeCommands = EnumSet.of(CommandType.LOGOUT, CommandType.CREATE_VACANCY, CommandType.DELETE_VACANCY,
                CommandType.RESTORE_VACANCY, CommandType.TO_EMPLOYEE_VACANCIES, CommandType.TO_EMPLOYEE_VACANCY_INFO,
                CommandType.SEE_ACTIVE_EMPLOYEE_VACANCIES, CommandType.SEE_DELETED_EMPLOYEE_VACANCIES,
                CommandType.SEE_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS, CommandType.CREATE_INTERVIEW_RESULT,
                CommandType.SEE_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS, CommandType.EDIT_VACANCY_INFO,
                CommandType.TO_USER_PROFILE, CommandType.EDIT_USER_PROFILE, CommandType.TO_EMPLOYEE_APPLICANT_REQUEST,
                CommandType.SEE_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS, CommandType.PROVIDE_IMAGE,
                CommandType.SCHEDULE_TECHNICAL_INTERVIEW, CommandType.CHANGE_USER_PASSWORD, CommandType.DELETE_USER_ACCOUNT);
        employeeCommands.addAll(sameCommands);

        EnumSet<CommandType> adminCommands = EnumSet.of(CommandType.LOGOUT, CommandType.CREATE_VACANCY, CommandType.DELETE_VACANCY,
                CommandType.RESTORE_VACANCY, CommandType.TO_USER_PROFILE, CommandType.EDIT_USER_PROFILE, CommandType.PROVIDE_IMAGE,
                CommandType.CHANGE_USER_PASSWORD, CommandType.DELETE_USER_ACCOUNT, CommandType.TO_ADMIN_USER_LIST, CommandType.SEE_ACTIVE_USERS,
                CommandType.SEE_DELETED_USERS, CommandType.FIND_USERS_BY_KEY_WORD, CommandType.TO_ADMIN_USER_INFO, CommandType.BLOCK_USER,
                CommandType.UNBLOCK_USER, CommandType.CHANGE_USER_ROLE);
        adminCommands.addAll(sameCommands);

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
        EnumSet<CommandType> commands = permissionCommands.get(role);
        Optional<CommandType> command = CommandProvider.defineCommandType(request);
        if (commands == null || !command.isPresent() || !commands.contains(command.get())) {
            logger.log(Level.ERROR, "User hasn't got permission to execute command = " + command);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ServletAttribute.HOME_URL_PATTERN);
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
