package com.epam.hrsystem.controller.filter;

import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandEnum;
import com.epam.hrsystem.controller.command.CommandProvider;
import com.epam.hrsystem.model.entity.UserRole;

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
    private static final Map<UserRole, List<CommandEnum>> permissionCommands = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        List<CommandEnum> sameCommands = new ArrayList<>();
        sameCommands.add(CommandEnum.CHANGE_LANGUAGE);
        sameCommands.add(CommandEnum.TO_VACANCY);
        sameCommands.add(CommandEnum.VACANCY_INFO);
        sameCommands.add(CommandEnum.FIND_VACANCIES_BY_KEY_WORD);
        sameCommands.add(CommandEnum.SORT_VACANCIES_BY_DATE);

        List<CommandEnum> guestCommands = new ArrayList<>(sameCommands);
        guestCommands.add(CommandEnum.REGISTER);
        guestCommands.add(CommandEnum.LOGIN);
        guestCommands.add(CommandEnum.TO_LOGIN);
        guestCommands.add(CommandEnum.TO_REGISTER);

        //todo add commands
        List<CommandEnum> applicantCommands = new ArrayList<>(sameCommands);
        applicantCommands.add(CommandEnum.LOGOUT);

        List<CommandEnum> employeeCommands = new ArrayList<>(sameCommands);
        employeeCommands.add(CommandEnum.LOGOUT);
        employeeCommands.add(CommandEnum.CREATE_VACANCY);

        List<CommandEnum> adminCommands = new ArrayList<>(sameCommands);
        adminCommands.add(CommandEnum.LOGOUT);
        adminCommands.add(CommandEnum.CREATE_VACANCY);

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
        CommandEnum commandType = CommandProvider.defineCommandType(request).get();
        if (commands == null || !commands.contains(commandType)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(UrlPattern.PERMISSION_ERROR);
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}