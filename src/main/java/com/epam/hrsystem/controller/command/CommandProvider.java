package com.epam.hrsystem.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

/**
 * Class used to operate with commands.
 *
 * @author Aleksey Klevitov
 */
public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final String DO_SUBSTRING = ".do";
    private static final String SLASH = "/";

    private CommandProvider() {
    }

    /**
     * Defines an action command from the request.
     *
     * @param request HttpServletRequest object.
     * @return Optional object of action command if exists, Optional.empty() otherwise.
     */
    public static Optional<ActionCommand> defineCommand(HttpServletRequest request) {
        Optional<String> optionalCommand = parseCommandName(request.getRequestURI());
        if (optionalCommand.isPresent()) {
            String stringCommand = optionalCommand.get();
            try {
                CommandType commandType = CommandType.valueOf(stringCommand.toUpperCase());
                return Optional.of(commandType.getCurrentCommand());
            } catch (IllegalArgumentException e) {
                logger.log(Level.ERROR, "Command " + stringCommand + "isn't correct: " + e);
            }
        }
        return Optional.empty();
    }

    /**
     * Defines a command type from the request.
     *
     * @param request HttpServletRequest object.
     * @return Optional object of command type if exists, Optional.empty() otherwise.
     */
    public static Optional<CommandType> defineCommandType(HttpServletRequest request) {
        return (parseCommandName(request.getRequestURI()).map(s -> CommandType.valueOf(s.toUpperCase(Locale.ROOT))));
    }

    /**
     * Parses command name from String object.
     *
     * @param url String object of request's url.
     * @return String object of command's name.
     */
    public static Optional<String> parseCommandName(String url) {
        int doPosition = url.indexOf(DO_SUBSTRING);
        if (doPosition == -1) {
            return Optional.empty();
        }
        int lastSlashPosition = url.lastIndexOf(SLASH);
        return Optional.of(url.substring(lastSlashPosition + 1, doPosition));
    }
}
