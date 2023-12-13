package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.mapper.ExamMapper;
import me.ijusthaveto.exam.mapper.ExamquestionMapper;
import me.ijusthaveto.exam.mapper.QuestionMapper;
import me.ijusthaveto.exam.mapper.StudentexamMapper;
import me.ijusthaveto.exam.service.ExamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
}




