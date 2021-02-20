package com.epam.hrsystem.controller.command;

import com.epam.hrsystem.controller.command.impl.ChangeLanguageCommand;
import com.epam.hrsystem.controller.command.impl.CreateVacancyCommand;
import com.epam.hrsystem.controller.command.impl.CreateApplicantRequestCommand;
import com.epam.hrsystem.controller.command.impl.DeleteVacancyCommand;
import com.epam.hrsystem.controller.command.impl.FindVacanciesByKeyWordCommand;
import com.epam.hrsystem.controller.command.impl.LoginCommand;
import com.epam.hrsystem.controller.command.impl.LogoutCommand;
import com.epam.hrsystem.controller.command.impl.RegisterCommand;
import com.epam.hrsystem.controller.command.impl.RestoreVacancyCommand;
import com.epam.hrsystem.controller.command.impl.SeeAllVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SortVacanciesByDateCommand;
import com.epam.hrsystem.controller.command.impl.VacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToVacanciesCommand;

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
    TO_VACANCIES {{
        this.command = new ToVacanciesCommand();
    }},
    VACANCY_INFO {{
        this.command = new VacancyInfoCommand();
    }},
    CREATE_VACANCY {{
        this.command = new CreateVacancyCommand();
    }},
    FIND_VACANCIES_BY_KEY_WORD {{
        this.command = new FindVacanciesByKeyWordCommand();
    }},
    SORT_VACANCIES_BY_DATE {{
        this.command = new SortVacanciesByDateCommand();
    }},
    SEE_ALL_VACANCIES {{
        this.command = new SeeAllVacanciesCommand();
    }},
    DELETE_VACANCY {{
        this.command = new DeleteVacancyCommand();
    }},
    RESTORE_VACANCY {{
        this.command = new RestoreVacancyCommand();
    }},
    CREATE_APPLICANT_REQUEST {{
        this.command = new CreateApplicantRequestCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
