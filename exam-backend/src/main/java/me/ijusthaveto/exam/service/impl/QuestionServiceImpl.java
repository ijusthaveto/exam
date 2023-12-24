package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.constant.QuestionConstant;
import me.ijusthaveto.exam.domain.Bank;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.Subject;
import me.ijusthaveto.exam.domain.dto.QuestionDetail;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.mapper.BankMapper;
import me.ijusthaveto.exam.service.QuestionService;
import me.ijusthaveto.exam.mapper.QuestionMapper;
import me.ijusthaveto.exam.service.SubjectService;
import me.ijusthaveto.exam.utils.OwnUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private SubjectService subjectService;

    @Resource
    private BankMapper bankMapper;

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

//        if (singleList.size() < singleNum) {
//            throw new BusinessException(SINGLE_QUESTION_NUM_ERROR);
//        }
//
//        if (multipleList.size() < multipleNum) {
//            throw new BusinessException(MULTIPLE_QUESTION_NUM_ERROR);
//        }
//
//        if (boolList.size() < boolNum) {
//            throw new BusinessException(BOOL_QUESTION_NUM_ERROR);
//        }

        singleList = OwnUtil.selectRandomQuestions(singleList, singleNum);
        multipleList = OwnUtil.selectRandomQuestions(multipleList, multipleNum);
        boolList = OwnUtil.selectRandomQuestions(boolList, boolNum);

        ArrayList<Question> questionList = new ArrayList<>();
        questionList.addAll(singleList);
        questionList.addAll(multipleList);
        questionList.addAll(boolList);

        return questionList;
    }

    @Override
    public Integer selectQuestionNum(Integer bankId, String type) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Question::getBankId, bankId).eq(Question::getQuestionType, type);
        return Math.toIntExact(baseMapper.selectCount(queryWrapper));
    }

    @Override
    public Page<QuestionDetail> selectPage(int page, int size, String type) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Question::getQuestionId).eq(Question::getQuestionType, type);
        Page<Question> questionPage = new Page<>(page, size);
        this.page(questionPage, queryWrapper);

        List<Question> records = questionPage.getRecords();
        Page<QuestionDetail> detailPage = new Page<>();
        BeanUtils.copyProperties(questionPage, detailPage);

        List<QuestionDetail> collect = records.parallelStream().
                map(this::enhanceQuestion).collect(Collectors.toList());
        detailPage.setRecords(collect);

        return detailPage;
    }

    @Override
    public QuestionDetail getQuestionDetailById(Integer questionId) {
        Question question = baseMapper.selectById(questionId);
        return enhanceQuestion(question);
    }

    @Override
    public void modifyQuestionInfo(QuestionDetail dto) {
        Question question = new Question();
        BeanUtils.copyProperties(dto, question);

        String subjectName = dto.getSubjectName();
        String bankTitle = dto.getBankTitle();

        updateSubjectId(question, subjectName);
        updateBankId(question, bankTitle);

        int result = baseMapper.updateById(question);
        if (result != 1) {
            throw new BusinessException(MODIFY_QUESTION_INFO_ERROR);
        }
    }

    private void updateBankId(Question question, String bankTitle) {
        LambdaQueryWrapper<Bank> bw = new LambdaQueryWrapper<>();
        bw.eq(Bank::getBankTitle, bankTitle);
        long count = bankMapper.selectCount(bw);
        if (count == 1) {
            Bank bank = bankMapper.selectOne(bw);
            question.setBankId(bank.getBankId());
        } else if (count == 0) {
            Bank bank = new Bank    ();
            bank.setBankTitle(bankTitle);
            bankMapper.insert(bank);
            Bank one = bankMapper.selectOne(bw);
            question.setBankId(one.getBankId());
        }
    }

    private void updateSubjectId(Question question, String subjectName) {
        LambdaQueryWrapper<Subject> subjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        subjectLambdaQueryWrapper.eq(Subject::getSubjectName, subjectName);
        long count = subjectService.count(subjectLambdaQueryWrapper);
        if (count == 1) {
            Subject subject = subjectService.getOne(subjectLambdaQueryWrapper);
            question.setSubjectId(subject.getSubjectId());
        } else if (count == 0) {
            Subject subject = new Subject();
            subject.setSubjectName(subjectName);
            subjectService.save(subject);
            Subject one = subjectService.getOne(subjectLambdaQueryWrapper);
            question.setSubjectId(one.getSubjectId());
        }
    }

    private QuestionDetail enhanceQuestion(Question question) {
        QuestionDetail detail = new QuestionDetail();
        BeanUtils.copyProperties(question, detail);

        Subject subject = subjectService.getById(detail.getSubjectId());
        detail.setSubjectName(subject.getSubjectName());

        Bank bank = bankMapper.selectById(detail.getBankId());
        detail.setBankTitle(bank.getBankTitle());

        return detail;
    }
}




