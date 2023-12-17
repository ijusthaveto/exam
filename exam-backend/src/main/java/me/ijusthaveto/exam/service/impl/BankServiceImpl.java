package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.Bank;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.BankService;
import me.ijusthaveto.exam.mapper.BankMapper;
import me.ijusthaveto.exam.service.QuestionService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static me.ijusthaveto.exam.common.ErrorCode.BANK_IMPORT_FILE_ERROR;
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
}




















