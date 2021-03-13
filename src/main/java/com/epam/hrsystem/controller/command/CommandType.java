package com.epam.hrsystem.controller.command;

import com.epam.hrsystem.controller.command.impl.ChangeLanguageCommand;
import com.epam.hrsystem.controller.command.impl.CreateInterviewResultCommand;
import com.epam.hrsystem.controller.command.impl.CreateVacancyCommand;
import com.epam.hrsystem.controller.command.impl.CreateApplicantRequestCommand;
import com.epam.hrsystem.controller.command.impl.DeleteVacancyCommand;
import com.epam.hrsystem.controller.command.impl.EditUserProfileCommand;
import com.epam.hrsystem.controller.command.impl.EditVacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.FindVacanciesByKeyWordCommand;
import com.epam.hrsystem.controller.command.impl.LoginCommand;
import com.epam.hrsystem.controller.command.impl.LogoutCommand;
import com.epam.hrsystem.controller.command.impl.ProvideImageCommand;
import com.epam.hrsystem.controller.command.impl.RegisterCommand;
import com.epam.hrsystem.controller.command.impl.RestoreVacancyCommand;
import com.epam.hrsystem.controller.command.impl.ScheduleTechnicalInterviewCommand;
import com.epam.hrsystem.controller.command.impl.SeeActiveEmployeeVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SeeAllVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SeeDeletedEmployeeVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithActiveApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithNotActiveApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SortVacanciesByDateCommand;
import com.epam.hrsystem.controller.command.impl.ToApplicantRequestsCommand;
import com.epam.hrsystem.controller.command.impl.ToEmployeeApplicantRequestCommand;
import com.epam.hrsystem.controller.command.impl.ToEmployeeVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.ToEmployeeVacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToUserProfileCommand;
import com.epam.hrsystem.controller.command.impl.ToVacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToVacanciesCommand;

public enum CommandType {
    REGISTER {{
        this.command = new RegisterCommand();
    }},
    LOGIN {{
        this.command = new LoginCommand();
    }},
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    EDIT_USER_PROFILE {{
        this.command = new EditUserProfileCommand();
    }},
    CHANGE_LANGUAGE {{
        this.command = new ChangeLanguageCommand();
    }},
    TO_USER_PROFILE {{
        this.command = new ToUserProfileCommand();
    }},
    TO_APPLICANT_REQUESTS {{
        this.command = new ToApplicantRequestsCommand();
    }},
    TO_VACANCIES {{
        this.command = new ToVacanciesCommand();
    }},
    TO_EMPLOYEE_VACANCIES {{
        this.command = new ToEmployeeVacanciesCommand();
    }},
    TO_EMPLOYEE_VACANCY_INFO {{
        this.command = new ToEmployeeVacancyInfoCommand();
    }},
    TO_EMPLOYEE_APPLICANT_REQUEST {{
        this.command = new ToEmployeeApplicantRequestCommand();
    }},
    TO_VACANCY_INFO {{
        this.command = new ToVacancyInfoCommand();
    }},
    CREATE_VACANCY {{
        this.command = new CreateVacancyCommand();
    }},
    EDIT_VACANCY_INFO {{
        this.command = new EditVacancyInfoCommand();
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
    }},
    CREATE_INTERVIEW_RESULT {{
        this.command = new CreateInterviewResultCommand();
    }},
    SCHEDULE_TECHNICAL_INTERVIEW {{
        this.command = new ScheduleTechnicalInterviewCommand();
    }},
    PROVIDE_IMAGE {{
        this.command = new ProvideImageCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
