package me.ijusthaveto.exam.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.constant.ExamConstant;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.Task;
import me.ijusthaveto.exam.domain.User;
import me.ijusthaveto.exam.domain.dto.ExamDto;
import me.ijusthaveto.exam.domain.dto.QuestionDto;
import me.ijusthaveto.exam.domain.dto.TaskDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.mapper.*;
import me.ijusthaveto.exam.service.ExamService;
import me.ijusthaveto.exam.service.QuestionService;
import me.ijusthaveto.exam.service.TaskService;
import me.ijusthaveto.exam.utils.OwnUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static me.ijusthaveto.exam.common.ErrorCode.*;
import static me.ijusthaveto.exam.constant.ExamConstant.*;
import static me.ijusthaveto.exam.constant.RoleConstant.DEFAULT_ROLE;

/**
* @author 修雯天
* @description 针对表【exam】的数据库操作Service实现
* @createDate 2023-12-11 12:42:32
*/
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam>
    implements ExamService{

    @Resource
    private StudentexamMapper studentexamMapper;

    @Resource
    private ExamquestionMapper examquestionMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TaskService taskService;

    @Resource
    private QuestionService questionService;

    @Override
    public List<Exam> selectExamListById(Integer loginId) {

        List<Integer> examIds = studentexamMapper.getExamIdsByUserId(loginId);

        List<Exam> exams = new ArrayList<>();
        for (Integer examId : examIds) {
            Exam exam = baseMapper.selectById(examId);
            exams.add(exam);
        }

        return exams;
    }

    @Override
    public List<Question> selectQuestionListByExamId(Integer examId) {

        List<Integer> questionIds = examquestionMapper.getQuestionIdsByExamId(examId);

        List<Question> questions = new ArrayList<>();
        for (Integer questionId : questionIds) {
            Question question = questionMapper.selectById(questionId);
            questions.add(question);
        }

        return questions;
    }

    @Override
    public void addExam(ExamDto dto) {

        double singleSumScore = dto.getSingleScore() * dto.getSingleNum();
        double multipleSumScore = dto.getMultipleScore() * dto.getMultipleNum();
        double boolSumScore = dto.getBoolScore() * dto.getBoolNum();
        double sumScore = singleSumScore + multipleSumScore + boolSumScore;

        if (sumScore != ExamConstant.STANDARD_EXAM_SCORE) {
            throw new BusinessException(ErrorCode.EXAM_SUM_SCORE_ERROR);
        }

        Exam exam = new Exam();
        BeanUtils.copyProperties(dto, exam);
        baseMapper.insert(exam);
    }

    @Override
    public void addClassExam(TaskDto dto) {
        List<Integer> classIdList = dto.getClassIdList();
        classIdList.parallelStream().forEach(classId -> {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getClassId, classId).eq(User::getRoleId, DEFAULT_ROLE)
                    .select(User::getUserId);
            List<User> list = userMapper.selectList(queryWrapper);
            List<Task> collect = list.parallelStream().map(student -> {
                Task task = new Task();
                task.setStatus(NOT_START);
                task.setTaskId(dto.getTaskId());
                task.setScore(DEFAULT_SCORE);
                task.setUserId(student.getUserId());
                return task;
            }).collect(Collectors.toList());
            taskService.saveBatch(collect);
        });
    }

    @Override
    public List<QuestionDto> start(Integer examId) {
        Integer userId = (Integer) StpUtil.getSession().get("loginId");
        Task task = new Task();
        task.setStatus(NOT_START);
        task.setScore(DEFAULT_SCORE);
        task.setExamId(examId);
        task.setUserId(userId);
        task.setCreateTime(OwnUtil.getCurrentDate());
        task.setUpdateTime(OwnUtil.getCurrentDate());
        taskService.save(task);
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Task::getExamId, examId).eq(Task::getUserId, userId);
        long count = taskService.count(wrapper);
        if (count != 1) {
            throw new BusinessException(ENTER_EXAM_ERROR);
        }
        Task result = taskService.getOne(wrapper);
        Exam exam = baseMapper.selectById(result.getExamId());
        if (!exam.getStartTime().before(OwnUtil.getCurrentDate())) {
            throw new BusinessException(EXAM_NOT_START_ERROR);
        }
        List<Question> questionList = questionService.pickQuestion(exam.getBankId(),
                exam.getSingleNum(),
                exam.getMultipleNum(),
                exam.getBoolNum());
        List<QuestionDto> resultList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            resultList.add(questionDto);
        }
        result.setStatus(IS_START);
        taskService.updateById(result);
        return resultList;
    }

    @Override
    public void saveTask(Task task) {
        boolean success = taskService.updateById(task);
        if (!success) {
            throw new BusinessException(AUTO_UPDATE_TASK_ERROR);
        }
    }
}




