package com.epam.hrsystem.controller.command;

import com.epam.hrsystem.controller.command.impl.BlockUserCommand;
import com.epam.hrsystem.controller.command.impl.ChangeLanguageCommand;
import com.epam.hrsystem.controller.command.impl.ChangeUserPasswordCommand;
import com.epam.hrsystem.controller.command.impl.ChangeUserRoleCommand;
import com.epam.hrsystem.controller.command.impl.CreateInterviewResultCommand;
import com.epam.hrsystem.controller.command.impl.CreateUserReportCommand;
import com.epam.hrsystem.controller.command.impl.CreateUserReportResponseCommand;
import com.epam.hrsystem.controller.command.impl.CreateVacancyCommand;
import com.epam.hrsystem.controller.command.impl.CreateApplicantRequestCommand;
import com.epam.hrsystem.controller.command.impl.DeleteUserAccountCommand;
import com.epam.hrsystem.controller.command.impl.DeleteVacancyCommand;
import com.epam.hrsystem.controller.command.impl.EditUserProfileCommand;
import com.epam.hrsystem.controller.command.impl.EditVacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.FindReportsByKeyWordCommand;
import com.epam.hrsystem.controller.command.impl.FindUsersByKeyWordCommand;
import com.epam.hrsystem.controller.command.impl.FindVacanciesByKeyWordCommand;
import com.epam.hrsystem.controller.command.impl.LoginCommand;
import com.epam.hrsystem.controller.command.impl.LogoutCommand;
import com.epam.hrsystem.controller.command.impl.ProvideImageCommand;
import com.epam.hrsystem.controller.command.impl.RegisterCommand;
import com.epam.hrsystem.controller.command.impl.RestoreVacancyCommand;
import com.epam.hrsystem.controller.command.impl.ScheduleTechnicalInterviewCommand;
import com.epam.hrsystem.controller.command.impl.SeeActiveEmployeeVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SeeActiveUsersCommand;
import com.epam.hrsystem.controller.command.impl.SeeAvailableReportsCommand;
import com.epam.hrsystem.controller.command.impl.SeeDeletedEmployeeVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.SeeDeletedReportsCommand;
import com.epam.hrsystem.controller.command.impl.SeeDeletedUsersCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithActiveApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SeeEmployeeVacanciesWithNotActiveApplicantsRequestsCommand;
import com.epam.hrsystem.controller.command.impl.SortVacanciesByDateCommand;
import com.epam.hrsystem.controller.command.impl.ToAdminUserInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToAdminUserReportInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToAdminUserReportListCommand;
import com.epam.hrsystem.controller.command.impl.ToApplicantRequestsCommand;
import com.epam.hrsystem.controller.command.impl.ToEmployeeApplicantRequestCommand;
import com.epam.hrsystem.controller.command.impl.ToEmployeeVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.ToEmployeeVacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToAdminUserListCommand;
import com.epam.hrsystem.controller.command.impl.ToUserProfileCommand;
import com.epam.hrsystem.controller.command.impl.ToVacancyInfoCommand;
import com.epam.hrsystem.controller.command.impl.ToVacanciesCommand;
import com.epam.hrsystem.controller.command.impl.UnblockUserCommand;

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
    TO_ADMIN_USER_LIST {{
        this.command = new ToAdminUserListCommand();
    }},
    TO_ADMIN_USER_INFO {{
        this.command = new ToAdminUserInfoCommand();
    }},
    TO_ADMIN_USER_REPORT_LIST {{
        this.command = new ToAdminUserReportListCommand();
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
    FIND_USERS_BY_KEY_WORD {{
        this.command = new FindUsersByKeyWordCommand();
    }},
    SORT_VACANCIES_BY_DATE {{
        this.command = new SortVacanciesByDateCommand();
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
    SEE_ACTIVE_USERS {{
        this.command = new SeeActiveUsersCommand();
    }},
    SEE_DELETED_USERS {{
        this.command = new SeeDeletedUsersCommand();
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
    }},
    DELETE_USER_ACCOUNT {{
        this.command = new DeleteUserAccountCommand();
    }},
    CHANGE_USER_PASSWORD {{
        this.command = new ChangeUserPasswordCommand();
    }},
    BLOCK_USER {{
        this.command = new BlockUserCommand();
    }},
    UNBLOCK_USER {{
        this.command = new UnblockUserCommand();
    }},
    CHANGE_USER_ROLE {{
        this.command = new ChangeUserRoleCommand();
    }},
    CREATE_USER_REPORT {{
        this.command = new CreateUserReportCommand();
    }},
    SEE_AVAILABLE_REPORTS {{
        this.command = new SeeAvailableReportsCommand();
    }},
    SEE_DELETED_REPORTS {{
        this.command = new SeeDeletedReportsCommand();
    }},
    FIND_REPORTS_BY_KEY_WORD {{
        this.command = new FindReportsByKeyWordCommand();
    }},
    TO_ADMIN_USER_REPORT_INFO {{
        this.command = new ToAdminUserReportInfoCommand();
    }},
    CREATE_USER_REPORT_RESPONSE {{
        this.command = new CreateUserReportResponseCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
