package me.ijusthaveto.exam.service.impl;

import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ExamServiceImplTest {

    @Resource
    private ExamService examService;

    @Test
    void selectExamListById() {
        Integer loginId = 1;
        List<Exam> exams = examService.selectExamListById(loginId);
        for (Exam exam : exams) {
            System.out.println(exam.toString());
        }
    }

    @Test
    void selectQuestionListByExamId() {
        Integer examId = 1;
        List<Question> questions = examService.selectQuestionListByExamId(examId);
        for (Question question : questions) {
            System.out.println(question.toString());
        }
    }
}
