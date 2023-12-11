package me.ijusthaveto.exam.controller;

import lombok.extern.slf4j.Slf4j;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.service.ExamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/exam")
public class ExamController {
    @Resource
    private ExamService examService;

    /**
     * 获取学生所有考试列表
     * @return
     */
    @GetMapping("/list/{loginId}")
    public BaseResponse<List<Exam>> list(@PathVariable Integer loginId) {
        List<Exam> examList = examService.selectExamListById(loginId);
        return ResultUtils.success(examList);
    }

    /**
     * 根据考试ID查询题目列表
     * @param examId
     * @return
     */
    @GetMapping("/{examId}")
    public BaseResponse<List<Question>> listQuestion(@PathVariable Integer examId) {
        List<Question> questionList = examService.selectQuestionListByExamId(examId);
        return ResultUtils.success(questionList);
    }

}
