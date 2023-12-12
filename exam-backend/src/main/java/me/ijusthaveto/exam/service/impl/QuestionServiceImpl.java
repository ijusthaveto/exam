package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.constant.ResultConstant;
import me.ijusthaveto.exam.domain.Examquestion;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.mapper.ExamquestionMapper;
import me.ijusthaveto.exam.service.ExamquestionService;
import me.ijusthaveto.exam.service.QuestionService;
import me.ijusthaveto.exam.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.Result;

/**
* @author 修雯天
* @description 针对表【question】的数据库操作Service实现
* @createDate 2023-12-10 13:14:24
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    @Resource
    private ExamquestionMapper examquestionMapper;

    @Override
    public void deleteQuestion(Integer questionId) {

        Question question = baseMapper.selectById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.QUESTION_NOT_EXISTS);
        }

        LambdaQueryWrapper<Examquestion> examQuestionWrapper = new LambdaQueryWrapper<>();
        examQuestionWrapper.eq(Examquestion::getQuestionId, questionId);
        Long relatedCount = examquestionMapper.selectCount(examQuestionWrapper);

        if (relatedCount > 0) {
            examquestionMapper.removeQuestionAndRelatedExamRecord(questionId);
            return;
        }

        baseMapper.deleteById(questionId);
    }
}




