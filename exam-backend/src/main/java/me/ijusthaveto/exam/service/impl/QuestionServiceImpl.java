package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.constant.QuestionConstant;
import me.ijusthaveto.exam.constant.ResultConstant;
import me.ijusthaveto.exam.domain.Examquestion;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.QuestionDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.mapper.ExamquestionMapper;
import me.ijusthaveto.exam.service.ExamquestionService;
import me.ijusthaveto.exam.service.QuestionService;
import me.ijusthaveto.exam.mapper.QuestionMapper;
import me.ijusthaveto.exam.utils.OwnUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

import static me.ijusthaveto.exam.common.ErrorCode.*;

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

    @Override
    public List<Question> pickQuestion(Integer bankId,
                                          Integer singleNum,
                                          Integer multipleNum,
                                          Integer boolNum) {
        LambdaQueryWrapper<Question> singleWrapper = new LambdaQueryWrapper<>();
        singleWrapper.eq(Question::getBankId, bankId)
                .eq(Question::getQuestionType, QuestionConstant.SINGLE_CHOICE);
        List<Question> singleList = baseMapper.selectList(singleWrapper);

        LambdaQueryWrapper<Question> multipleWrapper = new LambdaQueryWrapper<>();
        multipleWrapper.eq(Question::getBankId, bankId)
                .eq(Question::getQuestionType, QuestionConstant.MULTIPLE_CHOICE);
        List<Question> multipleList = baseMapper.selectList(multipleWrapper);

        LambdaQueryWrapper<Question> boolWrapper = new LambdaQueryWrapper<>();
        boolWrapper.eq(Question::getBankId, bankId)
                .eq(Question::getQuestionType, QuestionConstant.TRUE_OR_FALSE);
        List<Question> boolList = baseMapper.selectList(boolWrapper);

        if (singleList.size() < singleNum) {
            throw new BusinessException(SINGLE_QUESTION_NUM_ERROR);
        }

        if (multipleList.size() < multipleNum) {
            throw new BusinessException(MULTIPLE_QUESTION_NUM_ERROR);
        }

        if (boolList.size() < boolNum) {
            throw new BusinessException(BOOL_QUESTION_NUM_ERROR);
        }

        singleList = OwnUtil.selectRandomQuestions(singleList, singleNum);
        multipleList = OwnUtil.selectRandomQuestions(multipleList, multipleNum);
        boolList = OwnUtil.selectRandomQuestions(boolList, boolNum);

        ArrayList<Question> questionList = new ArrayList<>();
        questionList.addAll(singleList);
        questionList.addAll(multipleList);
        questionList.addAll(boolList);

        return questionList;
    }
}




