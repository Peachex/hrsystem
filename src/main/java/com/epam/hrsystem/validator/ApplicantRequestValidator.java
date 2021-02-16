package com.epam.hrsystem.validator;

import com.epam.hrsystem.model.entity.ApplicantState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ApplicantRequestValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final int SUMMARY_NAME_MAX_LENGTH = 50;

    private ApplicantRequestValidator() {
    }

    public static boolean isSummaryNameValid(String summaryName) {
        if (summaryName == null) {
            return false;
        }
        boolean result = summaryName.length() > 0 && summaryName.length() <= SUMMARY_NAME_MAX_LENGTH;
        if (!result) {
            logger.log(Level.DEBUG, "Summary name isn't valid: " + summaryName);
        }
        return result;
    }

    public static boolean isApplicantStateValid(String state) {
        boolean result = Arrays.stream(ApplicantState.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(state.toUpperCase());
        if (!result) {
            logger.log(Level.DEBUG, "Applicant state isn't valid: " + state);
        }
        return result;
    }
}
