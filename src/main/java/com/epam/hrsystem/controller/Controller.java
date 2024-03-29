package com.epam.hrsystem.controller;

import com.epam.hrsystem.controller.attribute.JspAttribute;
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

/**
 * Controller class used to process all requests from users with url pattern '*.do'.
 *
 * @author Aleksey Klevitov
 */
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
        Optional<ActionCommand> command = CommandProvider.defineCommand(request);
        try {
            CommandResult result = command.isPresent() ? command.get().execute(request, response) : new CommandResult(CommandResult.DEFAULT_PATH);
            result.redirect(request, response);
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
