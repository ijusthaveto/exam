package me.ijusthaveto.exam;

import cn.dev33.satoken.stp.StpUtil;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ExamApplicationTests {

    @Resource
    private ExamService examService;

    @Test
    void contextLoads() {
    }

    @Test
    void testStartExam() {
        Integer bankId = 15;
        List<Question> start = examService.start(bankId);
        System.out.println("Before--------------------------------------");
        for (Question question : start) {
            System.out.println("Hello World----------------------------");
            System.out.println(question.toString());
        }
        System.out.println("After--------------------------------------");

    }

}
