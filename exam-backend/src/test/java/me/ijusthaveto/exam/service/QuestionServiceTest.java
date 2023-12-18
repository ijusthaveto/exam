package me.ijusthaveto.exam.service;

import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.QuestionDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {

    @Resource
    private QuestionService questionService;

    @Test
    public void testRandomGenerateQuestions() {
        Integer bankId = 4;
        Integer singleNum = 10;
        Integer multipleNum = 10;
        Integer boolNum = 15;

        List<Question> questionList = questionService.pickQuestion(bankId, singleNum, multipleNum, boolNum);
        for (Question question : questionList) {
            System.out.println(question.toString());
        }

    }

}
