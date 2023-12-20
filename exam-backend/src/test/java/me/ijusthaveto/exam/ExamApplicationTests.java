package me.ijusthaveto.exam;

import cn.dev33.satoken.stp.StpUtil;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.QuestionDto;
import me.ijusthaveto.exam.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class ExamApplicationTests {

    @Resource
    private ExamService examService;


    @Test
    void contextLoads() {
    }

    @Test
    void testStartExam() {
        StpUtil.login(1);
        StpUtil.getSession().set("loginId", 1);
        List<QuestionDto> start = examService.start(15);
        if (Objects.isNull(start)) {
            System.out.println("QuestionDtoList is null!");
        }
        for (QuestionDto item : start) {
            System.out.println(item.getQuestionContent());
        }
    }

}
