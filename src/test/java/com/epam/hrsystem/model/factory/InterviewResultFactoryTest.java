package com.epam.hrsystem.model.factory;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.factory.impl.FactoryHolder;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class InterviewResultFactoryTest {
    @DataProvider
    public Object[][] fieldsForInterviewResultCreationData() {
        HashMap<String, String> correctFields = new HashMap<>();
        correctFields.put(RequestParameter.INTERVIEW_RESULT_RATING, "9");
        correctFields.put(RequestParameter.INTERVIEW_RESULT_COMMENT, "Good student. Показал хороший результат.");

        HashMap<String, String> wrongFields = new HashMap<>();
        wrongFields.put(RequestParameter.INTERVIEW_RESULT_RATING, "11");
        wrongFields.put(RequestParameter.INTERVIEW_RESULT_COMMENT, "Rating couldn't be more than 10.");

        return new Object[][]{
                {correctFields, Optional.of(new InterviewResult((byte) 9, "Good student. Показал хороший результат."))},
                {wrongFields, Optional.empty()},
        };
    }

    @Test(dataProvider = "fieldsForInterviewResultCreationData")
    public void createTest(Map<String, String> fields, Optional<InterviewResult> expected) {
        EntityFactory<InterviewResult> factory = FactoryHolder.HOLDER.getInterviewResultFactory();
        Optional<InterviewResult> actual = factory.create(fields);
        assertEquals(actual, expected);
    }
}
