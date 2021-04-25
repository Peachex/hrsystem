package com.epam.hrsystem.controller.command;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.ServletAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class contains types of command result.
 *
 * @author Aleksey Klevitov
 */
public class CommandResult {
    /**
     * Constant that represents default path.
     */
    public static final String DEFAULT_PATH = ServletAttribute.HOME_URL_PATTERN;

    private String path;
    private Type type;

    /**
     * Enumeration of command result returned types.
     */
    public enum Type {
        /**
         * Represents the return type of a forward.
         */
        FORWARD,
        /**
         * Represents the return type of a redirect.
         */
        REDIRECT,
        /**
         * Represents the return type to the previous page with redirect.
         */
        RETURN_WITH_REDIRECT
    }

    /**
     * Constructs a CommandResult object.
     *
     * @param path String object of path.
     * @param type Type object.
     */
    public CommandResult(String path, Type type) {
        this.path = path;
        this.type = type;
    }

    /**
     * Constructs a CommandResult object.
     *
     * @param path String object of path.
     */
    public CommandResult(String path) {
        this.path = path;
        this.type = Type.FORWARD;
    }

    /**
     * Constructs a CommandResult object.
     *
     * @param type Type object.
     */
    public CommandResult(Type type) {
        this.type = type;
    }

    /**
     * Getter method of path.
     *
     * @return String object of path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter method of path.
     *
     * @param path String object of path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Getter method of type.
     *
     * @return Type object.
     */
    public Type getType() {
        return type;
    }

    /**
     * Setter method of type.
     *
     * @param type Type object.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Provides path or default path.
     *
     * @return String object of path.
     */
    public String providePath() {
        return (path == null || path.isEmpty() ? DEFAULT_PATH : path);
    }

    /**
     * Redirects to the required page (command).
     *
     * @param request  Request object.
     * @param response Response object.
     * @throws ServletException if an exception has occurred while executing.
     * @throws IOException      if an exception has occurred while executing.
     */
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandResult.Type commandType = this.getType();
        if (commandType == CommandResult.Type.FORWARD) {
            request.getRequestDispatcher(this.providePath()).forward(request, response);
            return;
        }
        if (commandType == CommandResult.Type.REDIRECT) {
            response.sendRedirect(request.getContextPath() + this.providePath());
            return;
        }
        if (commandType == CommandResult.Type.RETURN_WITH_REDIRECT) {
            String previousUrl = request.getHeader(RequestParameter.HEADER_REFERER);
            if (previousUrl == null || previousUrl.isEmpty()) {
                previousUrl = request.getContextPath() + CommandResult.DEFAULT_PATH;
            }
            response.sendRedirect(previousUrl);
        }
    }
}
