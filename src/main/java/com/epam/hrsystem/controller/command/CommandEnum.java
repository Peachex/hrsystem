package com.epam.hrsystem.controller.command;


import com.epam.hrsystem.controller.command.impl.ChangeLanguageCommand;
import com.epam.hrsystem.controller.command.impl.LoginCommand;
import com.epam.hrsystem.controller.command.impl.LogoutCommand;
import com.epam.hrsystem.controller.command.impl.RegisterCommand;
import com.epam.hrsystem.controller.command.impl.ToLoginCommand;
import com.epam.hrsystem.controller.command.impl.ToRegisterCommand;
import com.epam.hrsystem.controller.command.impl.VacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToVacancyCommand;

public enum CommandEnum {
    REGISTER {{
        this.command = new RegisterCommand();
    }},
    TO_REGISTER{{
       this.command = new ToRegisterCommand();
    }},
    LOGIN {{
        this.command = new LoginCommand();
    }},
    TO_LOGIN {{
        this.command = new ToLoginCommand();
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
