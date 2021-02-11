package com.epam.hrsystem.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

public class CommandProvider {
    private static final String DO_SUBSTRING = ".do";

    private static final String SLASH = "/";

    private CommandProvider() {
    }

    public static Optional<ActionCommand> defineCommand(HttpServletRequest request) {
        Optional<ActionCommand> result = Optional.empty();
        String url = request.getRequestURI();
        String stringCommand = parseCommandName(url);
        if (stringCommand != null && !stringCommand.isEmpty()) {
            CommandEnum commandEnum = CommandEnum.valueOf(stringCommand.toUpperCase());
            ActionCommand command = commandEnum.getCurrentCommand();
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

    public static Optional<CommandEnum> defineCommandType(HttpServletRequest request) {
        Optional<CommandEnum> result = Optional.empty();
        String url = request.getRequestURI();
        String stringCommand = parseCommandName(url);
        if (stringCommand != null) {
            CommandEnum command = CommandEnum.valueOf(stringCommand.toUpperCase(Locale.ROOT));
            result = Optional.of(command);
        }
        return result;
    }
}
