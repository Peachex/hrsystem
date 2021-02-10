package com.epam.hrsystem.controller.command;


import com.epam.hrsystem.controller.command.impl.RegisterCommand;

public enum CommandEnum {
    REGISTER {{
        this.command = new RegisterCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
