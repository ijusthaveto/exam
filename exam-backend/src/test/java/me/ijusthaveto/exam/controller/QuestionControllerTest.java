package me.ijusthaveto.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.ijusthaveto.exam.constant.QuestionConstant;
import me.ijusthaveto.exam.constant.ResultConstant;
import me.ijusthaveto.exam.domain.Examquestion;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.service.ExamquestionService;
import me.ijusthaveto.exam.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class QuestionControllerTest {
    @Resource
    private QuestionService questionService;

    @Resource
    private ExamquestionService examquestionService;

    @Test
    public void testSaveQuestion() {

        final Integer bankId = 1;
        final String questionContent = "What is the capital of China?";
        final String options = "{\"A\": \"Beijing\", \"B\": \"HongKong\", \"C\": \"Shanghai\", \"D\": \"Chongqing\"}";
        final String correctAnswer = "A";
        final Integer score = 2;

        Question question = new Question();
        question.setQuestionBankId(1);
        question.setDifficultyLevel(QuestionConstant.EASY_LEVEL);
        question.setQuestionType(QuestionConstant.SINGLE_CHOICE);
        question.setQuestionContent(questionContent);
        question.setOptions(options);
        question.setCorrectAnswer(correctAnswer);
        question.setScore(score);

        boolean result = questionService.save(question);
        if (result) {
            LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Question::getQuestionContent, questionContent);
            Question one = questionService.getOne(wrapper);
            if (one != null) {
                System.out.println(one.toString());
            }
        }

    }

    @Test
    public void testRemoveQuestion() {
        final Integer questionId = 10;
        boolean removeResult = questionService.removeById(questionId);
        LambdaQueryWrapper<Examquestion> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Examquestion::getQuestionId, questionId);
        boolean anotherRemoveResult = examquestionService.remove(wrapper);

        if (removeResult && anotherRemoveResult) {
            System.out.println(ResultConstant.REMOVE_QUESTION_SUCCESS);
        } else {
            System.out.println(ResultConstant.REMOVE_QUESTION_ERROR);
        }
    }


}
