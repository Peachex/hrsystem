package com.epam.hrsystem.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final String DO_SUBSTRING = ".do";
    private static final String SLASH = "/";

    private CommandProvider() {
    }

    public static Optional<ActionCommand> defineCommand(HttpServletRequest request) {
        Optional<ActionCommand> result = Optional.empty();
        String url = request.getRequestURI();
        String stringCommand = parseCommandName(url);
        if (stringCommand != null && !stringCommand.isEmpty()) {
            try {
                CommandType commandType = CommandType.valueOf(stringCommand.toUpperCase());
                ActionCommand command = commandType.getCurrentCommand();
                result = Optional.of(command);
            } catch (IllegalArgumentException e) {
                logger.log(Level.ERROR, "Command " + stringCommand + "isn't correct: " + e);
            }

        }
        return result;
    }

    public static Optional<CommandType> defineCommandType(HttpServletRequest request) {
        Optional<CommandType> result = Optional.empty();
        String url = request.getRequestURI();
        String stringCommand = parseCommandName(url);
        if (stringCommand != null) {
            CommandType command = CommandType.valueOf(stringCommand.toUpperCase(Locale.ROOT));
            result = Optional.of(command);
        }
        return result;
    }

    public static String parseCommandName(String url) {
        String commandName;
        int doPosition = url.indexOf(DO_SUBSTRING);
        if (doPosition == -1) {
            return null;
        }
        int lastSlashPosition = url.lastIndexOf(SLASH);
        commandName = url.substring(lastSlashPosition + 1, doPosition);
        return commandName;
    }
}
