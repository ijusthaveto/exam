package me.ijusthaveto.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;

import java.util.List;

/**
* @author 修雯天
* @description 针对表【exam】的数据库操作Service
* @createDate 2023-12-11 12:42:32
*/
public interface ExamService extends IService<Exam> {

    List<Exam> selectExamListById(Integer loginId);

    List<Question> selectQuestionListByExamId(Integer examId);
}
