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
import com.epam.hrsystem.controller.command.impl.SeeActiveEmployeeVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SeeAllVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SeeDeletedEmployeeVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithActiveApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithNotActiveApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SortVacanciesByDateCommand;
import com.epam.hrsystem.controller.command.impl.ToEmployeeVacanciesCommand;
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
    TO_EMPLOYEE_VACANCIES {{
        this.command = new ToEmployeeVacanciesCommand();
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
    SEE_ACTIVE_EMPLOYEE_VACANCIES {{
        this.command = new SeeActiveEmployeeVacanciesCommand();
    }},
    SEE_DELETED_EMPLOYEE_VACANCIES {{
        this.command = new SeeDeletedEmployeeVacanciesCommand();
    }},
    SEE_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS {{
        this.command = new SeeEmployeeVacanciesWithApplicantsRequestsCommand();
    }},
    SEE_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS {{
        this.command = new SeeEmployeeVacanciesWithActiveApplicantsRequestsCommand();
    }},
    SEE_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS {{
        this.command = new SeeEmployeeVacanciesWithNotActiveApplicantsRequestsCommand();
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
