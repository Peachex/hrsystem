package com.epam.hrsystem.controller.command;


import com.epam.hrsystem.controller.command.impl.ChangeLanguageCommand;
import com.epam.hrsystem.controller.command.impl.CreateVacancyCommand;
import com.epam.hrsystem.controller.command.impl.FindVacanciesByKeyWordCommand;
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
    TO_REGISTER {{
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
    TO_VACANCY {{
        this.command = new ToVacancyCommand();
    }},
    VACANCY_INFO {{
        this.command = new VacancyInfoCommand();
    }},
    CREATE_VACANCY {{
        this.command = new CreateVacancyCommand();
    }},
    FIND_VACANCIES_BY_KEY_WORD {{
        this.command = new FindVacanciesByKeyWordCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
