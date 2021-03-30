package com.epam.hrsystem.validator;

import com.epam.hrsystem.model.entity.ApplicantState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApplicantRequestValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern SUMMARY_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,10000}");
    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("([1-2][0-9]{3})-([0][1-9]|[1][0-2])-([0][1-9]|[12][0-9]|[3][01])");

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
        if (state == null || state.isEmpty()) {
            return false;
        }
        boolean result = Arrays.stream(ApplicantState.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(state.toUpperCase());
        if (!result) {
            logger.log(Level.DEBUG, "Applicant state isn't valid: " + state);
        }
        return result;
    }

    public static boolean isTechnicalInterviewDateValid(String date) {
        if (date == null) {
            return false;
        }
        Matcher matcher = DATE_FORMAT_PATTERN.matcher(date);
        boolean result = matcher.matches() && LocalDate.parse(date).isAfter(LocalDate.now());
        if (!result) {
            logger.log(Level.DEBUG, "Date isn't valid: " + date);
        }
        return result;

    }
}
