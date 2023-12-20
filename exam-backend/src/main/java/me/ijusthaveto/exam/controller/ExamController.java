package me.ijusthaveto.exam.controller;

import lombok.extern.slf4j.Slf4j;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.constant.ResultConstant;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.Task;
import me.ijusthaveto.exam.domain.dto.ExamDto;
import me.ijusthaveto.exam.domain.dto.QuestionDto;
import me.ijusthaveto.exam.domain.dto.TaskDto;
import me.ijusthaveto.exam.service.ExamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static me.ijusthaveto.exam.constant.ResultConstant.ADD_EXAM_SUCCESS;
import static me.ijusthaveto.exam.constant.ResultConstant.AUTO_SAVE_TASK_SUCCESS;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/exam")
public class ExamController {
    @Resource
    private ExamService examService;

    @PostMapping("/add")
    public BaseResponse<String> addExam(@RequestBody ExamDto dto) {
        examService.addExam(dto);
        return ResultUtils.success(ADD_EXAM_SUCCESS);
    }

    /**
     * 获取学生所有考试列表
     *
     * @return
     */
    @GetMapping("/list/{loginId}")
    public BaseResponse<List<Exam>> list(@PathVariable Integer loginId) {
        List<Exam> examList = examService.selectExamListById(loginId);
        return ResultUtils.success(examList);
    }

    /**
     * 根据考试ID查询题目列表
     *
     * @param examId
     * @return
     */
    @GetMapping("/{examId}")
    public BaseResponse<List<Question>> listQuestion(@PathVariable Integer examId) {
        List<Question> questionList = examService.selectQuestionListByExamId(examId);
        return ResultUtils.success(questionList);
    }

    /**
     * 批量添加班级考试
     *
     * @param dto
     * @return
     */
    @PostMapping("/addClassExam")
    public BaseResponse<String> addClassExam(@RequestBody TaskDto dto) {
        examService.addClassExam(dto);
        return ResultUtils.success(ADD_EXAM_SUCCESS);
    }

    /**
     * 开始考试
     *
     * @param examId
     * @return
     */
    @GetMapping("/start/{examId}")
    public BaseResponse<List<Question>> startExam(@PathVariable("examId") Integer examId) {
        List<Question> questionDtoList = examService.start(examId);
        log.info("questionList: ");
        return ResultUtils.success(questionDtoList);
    }

    @PostMapping("/autoSave")
    public BaseResponse<String> autoSaveTask(@RequestBody Task task) {
        examService.saveTask(task);
        return ResultUtils.success(AUTO_SAVE_TASK_SUCCESS);
    }
}
