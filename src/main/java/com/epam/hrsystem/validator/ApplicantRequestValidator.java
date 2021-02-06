package com.epam.hrsystem.validator;

import com.epam.hrsystem.model.entity.ApplicantState;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ApplicantRequestValidator {
    private static final int SUMMARY_NAME_MAX_LENGTH = 50;

    public static boolean isSummaryNameValid(String summaryName) {
        if (summaryName == null) {
            return false;
        }
        return (summaryName.length() > 0 && summaryName.length() < SUMMARY_NAME_MAX_LENGTH);
    }

    public static boolean isApplicantStateValid(String state) {
        boolean result = Arrays.stream(ApplicantState.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(state.toUpperCase());
        return result;

    }
}
