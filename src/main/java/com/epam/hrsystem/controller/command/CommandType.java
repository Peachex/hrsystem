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

/**
 * Enumeration of command types.
 *
 * @author Aleksey Klevitov
 */
public enum CommandType {
    /**
     * Represents register command implementation of ActionCommand interface.
     */
    REGISTER {{
        this.command = new RegisterCommand();
    }},
    /**
     * Represents login command implementation of ActionCommand interface.
     */
    LOGIN {{
        this.command = new LoginCommand();
    }},
    /**
     * Represents logout command implementation of ActionCommand interface.
     */
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    /**
     * Represents edit user profile command implementation of ActionCommand interface.
     */
    EDIT_USER_PROFILE {{
        this.command = new EditUserProfileCommand();
    }},
    /**
     * Represents change language command implementation of ActionCommand interface.
     */
    CHANGE_LANGUAGE {{
        this.command = new ChangeLanguageCommand();
    }},
    /**
     * Represents to user profile command implementation of ActionCommand interface.
     */
    TO_USER_PROFILE {{
        this.command = new ToUserProfileCommand();
    }},
    /**
     * Represents to applicant requests command implementation of ActionCommand interface.
     */
    TO_APPLICANT_REQUESTS {{
        this.command = new ToApplicantRequestsCommand();
    }},
    /**
     * Represents to vacancies command implementation of ActionCommand interface.
     */
    TO_VACANCIES {{
        this.command = new ToVacanciesCommand();
    }},
    /**
     * Represents to employee vacancies command implementation of ActionCommand interface.
     */
    TO_EMPLOYEE_VACANCIES {{
        this.command = new ToEmployeeVacanciesCommand();
    }},
    /**
     * Represents to employee vacancy info command implementation of ActionCommand interface.
     */
    TO_EMPLOYEE_VACANCY_INFO {{
        this.command = new ToEmployeeVacancyInfoCommand();
    }},
    /**
     * Represents to employee applicant request command implementation of ActionCommand interface.
     */
    TO_EMPLOYEE_APPLICANT_REQUEST {{
        this.command = new ToEmployeeApplicantRequestCommand();
    }},
    /**
     * Represents to vacancy info command implementation of ActionCommand interface.
     */
    TO_VACANCY_INFO {{
        this.command = new ToVacancyInfoCommand();
    }},
    /**
     * Represents to admin user list command implementation of ActionCommand interface.
     */
    TO_ADMIN_USER_LIST {{
        this.command = new ToAdminUserListCommand();
    }},
    /**
     * Represents to admin user info command implementation of ActionCommand interface.
     */
    TO_ADMIN_USER_INFO {{
        this.command = new ToAdminUserInfoCommand();
    }},
    /**
     * Represents to admin user report list command implementation of ActionCommand interface.
     */
    TO_ADMIN_USER_REPORT_LIST {{
        this.command = new ToAdminUserReportListCommand();
    }},
    /**
     * Represents create vacancy command implementation of ActionCommand interface.
     */
    CREATE_VACANCY {{
        this.command = new CreateVacancyCommand();
    }},
    /**
     * Represents edit vacancy info command implementation of ActionCommand interface.
     */
    EDIT_VACANCY_INFO {{
        this.command = new EditVacancyInfoCommand();
    }},
    /**
     * Represents find vacancies by key word command implementation of ActionCommand interface.
     */
    FIND_VACANCIES_BY_KEY_WORD {{
        this.command = new FindVacanciesByKeyWordCommand();
    }},
    /**
     * Represents find users by key word command implementation of ActionCommand interface.
     */
    FIND_USERS_BY_KEY_WORD {{
        this.command = new FindUsersByKeyWordCommand();
    }},
    /**
     * Represents sort vacancies by date command implementation of ActionCommand interface.
     */
    SORT_VACANCIES_BY_DATE {{
        this.command = new SortVacanciesByDateCommand();
    }},
    /**
     * Represents see active employee vacancies command implementation of ActionCommand interface.
     */
    SEE_ACTIVE_EMPLOYEE_VACANCIES {{
        this.command = new SeeActiveEmployeeVacanciesCommand();
    }},
    /**
     * Represents see deleted employee vacancies command implementation of ActionCommand interface.
     */
    SEE_DELETED_EMPLOYEE_VACANCIES {{
        this.command = new SeeDeletedEmployeeVacanciesCommand();
    }},
    /**
     * Represents see employee vacancies with applicants requests command implementation of ActionCommand interface.
     */
    SEE_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS {{
        this.command = new SeeEmployeeVacanciesWithApplicantsRequestsCommand();
    }},
    /**
     * Represents see employee vacancies with active applicants requests command implementation of ActionCommand interface.
     */
    SEE_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS {{
        this.command = new SeeEmployeeVacanciesWithActiveApplicantsRequestsCommand();
    }},
    /**
     * Represents see employee vacancies with not active applicants requests command implementation of ActionCommand interface.
     */
    SEE_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS {{
        this.command = new SeeEmployeeVacanciesWithNotActiveApplicantsRequestsCommand();
    }},
    /**
     * Represents see active users command implementation of ActionCommand interface.
     */
    SEE_ACTIVE_USERS {{
        this.command = new SeeActiveUsersCommand();
    }},
    /**
     * Represents see deleted users command implementation of ActionCommand interface.
     */
    SEE_DELETED_USERS {{
        this.command = new SeeDeletedUsersCommand();
    }},
    /**
     * Represents delete vacancy command implementation of ActionCommand interface.
     */
    DELETE_VACANCY {{
        this.command = new DeleteVacancyCommand();
    }},
    /**
     * Represents restore vacancy command implementation of ActionCommand interface.
     */
    RESTORE_VACANCY {{
        this.command = new RestoreVacancyCommand();
    }},
    /**
     * Represents create applicant request command implementation of ActionCommand interface.
     */
    CREATE_APPLICANT_REQUEST {{
        this.command = new CreateApplicantRequestCommand();
    }},
    /**
     * Represents create interview result command implementation of ActionCommand interface.
     */
    CREATE_INTERVIEW_RESULT {{
        this.command = new CreateInterviewResultCommand();
    }},
    /**
     * Represents schedule technical interview command implementation of ActionCommand interface.
     */
    SCHEDULE_TECHNICAL_INTERVIEW {{
        this.command = new ScheduleTechnicalInterviewCommand();
    }},
    /**
     * Represents provide image command implementation of ActionCommand interface.
     */
    PROVIDE_IMAGE {{
        this.command = new ProvideImageCommand();
    }},
    /**
     * Represents delete user account command implementation of ActionCommand interface.
     */
    DELETE_USER_ACCOUNT {{
        this.command = new DeleteUserAccountCommand();
    }},
    /**
     * Represents change user password command implementation of ActionCommand interface.
     */
    CHANGE_USER_PASSWORD {{
        this.command = new ChangeUserPasswordCommand();
    }},
    /**
     * Represents block user command implementation of ActionCommand interface.
     */
    BLOCK_USER {{
        this.command = new BlockUserCommand();
    }},
    /**
     * Represents unblock user command implementation of ActionCommand interface.
     */
    UNBLOCK_USER {{
        this.command = new UnblockUserCommand();
    }},
    /**
     * Represents change user role command implementation of ActionCommand interface.
     */
    CHANGE_USER_ROLE {{
        this.command = new ChangeUserRoleCommand();
    }},
    /**
     * Represents create user report command implementation of ActionCommand interface.
     */
    CREATE_USER_REPORT {{
        this.command = new CreateUserReportCommand();
    }},
    /**
     * Represents see available reports command implementation of ActionCommand interface.
     */
    SEE_AVAILABLE_REPORTS {{
        this.command = new SeeAvailableReportsCommand();
    }},
    /**
     * Represents see deleted reports command implementation of ActionCommand interface.
     */
    SEE_DELETED_REPORTS {{
        this.command = new SeeDeletedReportsCommand();
    }},
    /**
     * Represents find reports by key word command implementation of ActionCommand interface.
     */
    FIND_REPORTS_BY_KEY_WORD {{
        this.command = new FindReportsByKeyWordCommand();
    }},
    /**
     * Represents to admin user report info command implementation of ActionCommand interface.
     */
    TO_ADMIN_USER_REPORT_INFO {{
        this.command = new ToAdminUserReportInfoCommand();
    }},
    /**
     * Represents create user report response command implementation of ActionCommand interface.
     */
    CREATE_USER_REPORT_RESPONSE {{
        this.command = new CreateUserReportResponseCommand();
    }};

    /**
     * ActionCommand object of command.
     */
    ActionCommand command;

    /**
     * Getter method of ActionCommand's implementation.
     *
     * @return ActionCommand object.
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
