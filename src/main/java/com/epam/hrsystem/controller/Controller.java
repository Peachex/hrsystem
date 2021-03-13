package com.epam.hrsystem.controller;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.ServletAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandProvider;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = ServletAttribute.CONTROLLER_URL_PATTERN, name = ServletAttribute.CONTROLLER_SERVLET_NAME, loadOnStartup = 1)
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init() {
        ConnectionPool.ConnectionPoolHolder.POOL.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<ActionCommand> commandOptional = CommandProvider.defineCommand(request);
        try {
            CommandResult commandResult;
            if (commandOptional.isPresent()) {
                ActionCommand command = commandOptional.get();
                commandResult = command.execute(request, response);
            } else {
                commandResult = new CommandResult(CommandResult.DEFAULT_PATH);
            }
            String urlPath = commandResult.providePath();
            switch (commandResult.getType()) {
                case FORWARD: {
                    request.getRequestDispatcher(urlPath).forward(request, response);
                    break;
                }
                case REDIRECT: {
                    response.sendRedirect(request.getContextPath() + urlPath);
                    break;
                }
                case RETURN_WITH_FORWARD: {
                    String previousUrl = request.getHeader(RequestParameter.HEADER_REFERER);
                    request.getRequestDispatcher(previousUrl).forward(request, response);
                    break;
                }
                case RETURN_WITH_REDIRECT: {
                    String previousUrl = request.getHeader(RequestParameter.HEADER_REFERER);
                    response.sendRedirect(previousUrl);
                    break;
                }
            }
        } catch (CommandException e) {
            logger.log(Level.ERROR, "Couldn't process request: " + e);
            request.setAttribute(JspAttribute.ERROR_MESSAGE_INFO, e.getMessage());
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.ConnectionPoolHolder.POOL.getConnectionPool().destroyPool(this);
        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
