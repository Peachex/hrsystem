package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.UserServiceImpl;
import com.epam.hrsystem.model.service.impl.VacancyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class VacancyInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String vacancyId = request.getParameter(RequestParameter.VACANCY_ID);
        VacancyService service = VacancyServiceImpl.INSTANCE;
        CommandResult result;
        try {
            try {
                long id = Long.parseLong(vacancyId);
                Optional<Vacancy> vacancyOptional = service.findVacancyById(id);
                if (vacancyOptional.isPresent()) {
                    Vacancy vacancy = vacancyOptional.get();
                    request.setAttribute("vacancy", vacancy);
                    result = new CommandResult(UrlPattern.CURRENT_VACANCY, CommandResult.Type.FORWARD);
                } else {
                    result = new CommandResult(UrlPattern.VACANCY, CommandResult.Type.REDIRECT);
                    //todo error message
                }
            } catch (NumberFormatException e) {
                logger.log(Level.ERROR, e);
                {
                    result = new CommandResult(UrlPattern.VACANCY, CommandResult.Type.REDIRECT);
                }
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
