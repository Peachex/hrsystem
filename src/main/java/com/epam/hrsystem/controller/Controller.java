package com.epam.hrsystem.controller;

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

@WebServlet(urlPatterns = UrlPattern.CONTROLLER, name = "Controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init() {
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
                commandResult = command.execute(request);
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
            }
        } catch (CommandException e) {
            logger.log(Level.ERROR, "Couldn't process request: " + e);
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.POOL.destroyPool();
        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
