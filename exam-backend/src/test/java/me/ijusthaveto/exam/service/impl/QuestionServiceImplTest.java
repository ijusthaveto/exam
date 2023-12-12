package me.ijusthaveto.exam.service.impl;

import me.ijusthaveto.exam.constant.ResultConstant;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceImplTest {

    @Resource
    private QuestionService questionService;

    @Test
    public void testRemoveQuestion() {
        final Integer questionId = 16;
        questionService.deleteQuestion(questionId);
        Question byId = questionService.getById(questionId);
        Assertions.assertNull(byId, ResultConstant.REMOVE_QUESTION_SUCCESS);
    }

}
