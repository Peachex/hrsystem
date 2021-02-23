package com.epam.hrsystem.validator;

import com.epam.hrsystem.model.entity.ApplicantState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApplicantRequestValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern SUMMARY_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,10000}");

    private ApplicantRequestValidator() {
    }

    public static boolean isSummaryValid(String summary) {
        if (summary == null) {
            return false;
        }
        Matcher matcher = SUMMARY_PATTERN.matcher(summary);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Summary name isn't valid: " + summary);
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
