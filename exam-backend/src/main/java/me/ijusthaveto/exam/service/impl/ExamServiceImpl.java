package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.constant.ExamConstant;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.ExamDto;
import me.ijusthaveto.exam.exception.BusinessException;
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

    @Override
    public void addExam(ExamDto dto) {
//        1. 判断试卷的分值是否正常
//          1.1 计算试卷分数
        double singleSumScore = dto.getSingleScore() * dto.getSingleNum();
        double multipleSumScore = dto.getMultipleScore() * dto.getMultipleNum();
        double boolSumScore = dto.getBoolScore() * dto.getBoolNum();
        double sumScore = singleSumScore + multipleSumScore + boolSumScore;
//          1.2 将试卷分数与标准分数进行比较
        if (sumScore != ExamConstant.STANDARD_EXAM_SCORE) {
            throw new BusinessException(ErrorCode.EXAM_SUM_SCORE_ERROR);
        }
//        2. 保存试卷至数据库

    }
}




