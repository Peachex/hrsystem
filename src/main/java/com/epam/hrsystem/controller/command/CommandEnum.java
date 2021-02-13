package com.epam.hrsystem.controller.command;


import com.epam.hrsystem.controller.command.impl.ChangeLanguageCommand;
import com.epam.hrsystem.controller.command.impl.LoginCommand;
import com.epam.hrsystem.controller.command.impl.LogoutCommand;
import com.epam.hrsystem.controller.command.impl.RegisterCommand;
import com.epam.hrsystem.controller.command.impl.VacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToVacancyCommand;

public enum CommandEnum {
    REGISTER {{
        this.command = new RegisterCommand();
    }},
    LOGIN {{
        this.command = new LoginCommand();
    }},
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    CHANGE_LANGUAGE {{
        this.command = new ChangeLanguageCommand();
    }},
    VACANCY {{
        this.command = new ToVacancyCommand();
    }},
    VACANCY_INFO {{
        this.command = new VacancyInfoCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
