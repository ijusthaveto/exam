package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.constant.QuestionConstant;
import me.ijusthaveto.exam.domain.Bank;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.Subject;
import me.ijusthaveto.exam.domain.dto.BankDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.BankService;
import me.ijusthaveto.exam.mapper.BankMapper;
import me.ijusthaveto.exam.service.QuestionService;
import me.ijusthaveto.exam.service.SubjectService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static me.ijusthaveto.exam.common.ErrorCode.*;
import static me.ijusthaveto.exam.constant.QuestionConstant.*;

/**
* @author 修雯天
* @description 针对表【bank】的数据库操作Service实现
* @createDate 2023-12-17 18:20:22
*/
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank>
    implements BankService{

    @Resource
    private QuestionService questionService;


    @Resource
    private SubjectService subjectService;


    @Override
    public void processCsvFile(MultipartFile file, Integer subjectId, String bankTitle){
        Bank bank = new Bank();
        bank.setBankTitle(bankTitle);
        bank.setSubjectId(subjectId);
        baseMapper.insert(bank);

        LambdaQueryWrapper<Bank> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bank::getBankTitle, bankTitle);

        Bank one = baseMapper.selectOne(wrapper);
        Integer bankId = one.getBankId();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : parser) {
                Question question = new Question();
                question.setQuestionType(record.get(QUESTION_TYPE));
                question.setDifficultyLevel(record.get(DIFFICULTY_LEVEL));
                question.setQuestionContent(record.get(QUESTION_CONTENT));
                question.setOptions(record.get(OPTIONS));
                question.setCorrectAnswer(record.get(CORRECT_ANSWER));
                question.setScore(Integer.parseInt(record.get(SCORE)));
                question.setSubjectId(subjectId);
                question.setBankId(bankId);
                questionService.save(question);
            }
        } catch (Exception e) {
            throw new BusinessException(BANK_IMPORT_FILE_ERROR);
        }
    }

    @Override
    public Page<BankDto> selectPage(Integer page, Integer size, String bankTitle) {
        Page<Bank> bankPage = new Page<>();
        LambdaQueryWrapper<Bank> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(bankTitle != null, Bank::getBankTitle, bankTitle)
                .orderByAsc(Bank::getBankId);
        this.page(bankPage);

        Page<BankDto> bankDtoPage = new Page<>();
        BeanUtils.copyProperties(bankPage, bankDtoPage);
        List<Bank> records = bankPage.getRecords();

        List<BankDto> bankDtoList = records.parallelStream().map(bank -> {
            BankDto bankDto = new BankDto();
            bankDto.setBankId(bank.getBankId());
            bankDto.setBankTitle(bank.getBankTitle());
            bankDto.setSubjectId(bank.getSubjectId());
            LambdaQueryWrapper<Subject> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Subject::getSubjectId, bank.getSubjectId());
            Subject subject = subjectService.getOne(queryWrapper);
            bankDto.setSubjectName(subject.getSubjectName());
            bankDto.setSingleNum(questionService.selectQuestionNum(bank.getBankId(), SINGLE_CHOICE));
            bankDto.setMultipleNum(questionService.selectQuestionNum(bank.getBankId(), MULTIPLE_CHOICE));
            bankDto.setJudgeNum(questionService.selectQuestionNum(bank.getBankId(), TRUE_OR_FALSE));
            return bankDto;
        }).collect(Collectors.toList());
        bankDtoPage.setRecords(bankDtoList);
        return bankDtoPage;
    }

    @Override
    public List<Bank> selectAllList(Integer subjectId) {
        LambdaQueryWrapper<Bank> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(subjectId != null, Bank::getSubjectId, subjectId)
                .select(Bank::getBankId, Bank::getBankTitle);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void deleteWithQuestion(Integer bankId) {
        int i = baseMapper.deleteById(bankId);
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Question::getBankId, bankId);
        questionService.remove(queryWrapper);
    }

    @Override
    public BankDto getBankDtoById(Integer bankId) {
        Bank bank = baseMapper.selectById(bankId);
        BankDto bankDto = new BankDto();
        BeanUtils.copyProperties(bank, bankDto);
        Subject subject = subjectService.getById(bank.getSubjectId());

        bankDto.setSubjectName(subject.getSubjectName());
        bankDto.setSubjectId(subject.getSubjectId());
        bankDto.setSingleNum(questionService.selectQuestionNum(bankId, SINGLE_CHOICE));
        bankDto.setMultipleNum(questionService.selectQuestionNum(bankId, MULTIPLE_CHOICE));
        bankDto.setJudgeNum(questionService.selectQuestionNum(bankId, TRUE_OR_FALSE));

        return bankDto;
    }

    @Override
    public void modifyBankInfo(BankDto dto) {
        Bank bank = new Bank();
        BeanUtils.copyProperties(dto, bank);
        String subjectName = dto.getSubjectName();

        LambdaQueryWrapper<Subject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Subject::getSubjectName, subjectName);
        long count = subjectService.count(queryWrapper);
        if (count == 1) {
            Subject one = subjectService.getOne(queryWrapper);
            bank.setSubjectId(one.getSubjectId());
        } else if (count == 0) {
            Subject newSubject = new Subject();
            newSubject.setSubjectName(subjectName);
            subjectService.save(newSubject);
            Subject one = subjectService.getOne(queryWrapper);
            bank.setSubjectId(one.getSubjectId());
        }

        int result = baseMapper.updateById(bank);
        if (result != 1) {
            throw new BusinessException(MODIFY_BANK_INFO_ERROR);
        }
    }
}





















