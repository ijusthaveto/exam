package me.ijusthaveto.exam.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.constant.ResultConstant;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.Task;
import me.ijusthaveto.exam.domain.dto.*;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.ExamService;
import me.ijusthaveto.exam.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static me.ijusthaveto.exam.common.ErrorCode.REPEAT_EXAM_ERROR;
import static me.ijusthaveto.exam.constant.ResultConstant.*;

/**
 * @author ijusthaveto
 * @create 2023-12-22
 */
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/exam")
public class ExamController {
    @Resource
    private ExamService examService;

    @Resource
    private TaskService taskService;

    @PostMapping("/add")
    public BaseResponse<String> addExam(@RequestBody ExamDto dto) {
        examService.addExam(dto);
        return ResultUtils.success(ADD_EXAM_SUCCESS);
    }

    @GetMapping("/info/{examId}")
    public BaseResponse<Exam> selectExamInfo(@PathVariable("examId") Integer examId) {
        Exam byId = examService.getById(examId);
        return ResultUtils.success(byId);
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

    @PostMapping("/addTest")
    public BaseResponse<String> addTest(@RequestBody TestDto dto) {
        examService.addTest(dto);
        return ResultUtils.success(ADD_EXAM_SUCCESS);
    }

    /**
     * 开始考试
     *
     * @param examId
     * @return
     */
    @GetMapping("/start/{examId}")
    public BaseResponse<List<QuestionDto>> startExam(@PathVariable("examId") Integer examId) {

        List<QuestionDto> questionDtoList = examService.start(examId);
        return ResultUtils.success(questionDtoList);
    }

    @PostMapping("/autoSave")
    public BaseResponse<String> autoSaveTask(@RequestBody Task task) {
        examService.saveTask(task);
        return ResultUtils.success(AUTO_SAVE_TASK_SUCCESS);
    }

    @PostMapping("/auto")
    public BaseResponse<String> auto(@RequestBody CalcDto dto) {
        examService.auto(dto.getSingle(), dto.getMultiple(), dto.getJudge(), dto.getExamId());
        return ResultUtils.success(AUTO_SAVE_TASK_SUCCESS);
    }


    /**
     * 判断学生是否二次进入考试
     * @param examId
     * @return
     */
    @GetMapping("/status/{examId}")
    public BaseResponse<String> getExamStatus(@PathVariable("examId") Integer examId) {
        Integer loginId = (Integer) StpUtil.getSession().get("loginId");
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Task::getUserId, loginId).eq(Task::getExamId, examId);
        long count = taskService.count(wrapper);
        if (count != 0) {
            throw new BusinessException(REPEAT_EXAM_ERROR);
        }

        return ResultUtils.success(EXAM_STATUS_NORMAL);
    }
}
