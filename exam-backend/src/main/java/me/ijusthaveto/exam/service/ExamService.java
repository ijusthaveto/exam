package me.ijusthaveto.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.Task;
import me.ijusthaveto.exam.domain.dto.ExamDto;
import me.ijusthaveto.exam.domain.dto.QuestionDto;
import me.ijusthaveto.exam.domain.dto.TaskDto;
import me.ijusthaveto.exam.domain.dto.TestDto;

import java.util.List;

/**
* @author 修雯天
* @description 针对表【exam】的数据库操作Service
* @createDate 2023-12-11 12:42:32
*/
public interface ExamService extends IService<Exam> {

    List<Exam> selectExamListById(Integer loginId);

    List<Question> selectQuestionListByExamId(Integer examId);

    void addExam(ExamDto dto);

    void addClassExam(TaskDto dto);

    List<QuestionDto> start(Integer examId);

    void saveTask(Task task);

    void auto(List<QuestionDto> single, List<QuestionDto> multiple, List<QuestionDto> judge, Integer examId);

    void addTest(TestDto dto);

}
