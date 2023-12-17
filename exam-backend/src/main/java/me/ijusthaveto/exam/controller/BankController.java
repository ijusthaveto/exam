package me.ijusthaveto.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Bank;
import me.ijusthaveto.exam.domain.Subject;
import me.ijusthaveto.exam.domain.dto.BankDto;
import me.ijusthaveto.exam.service.BankService;
import me.ijusthaveto.exam.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static me.ijusthaveto.exam.constant.ResultConstant.IMPORT_BANK_SUCCESS;

/**
 * @author ijusthaveto
 * @create 2023-12-17
 */
@RestController
@RequestMapping("/bank")
@CrossOrigin
public class BankController {

    @Resource
    private BankService bankService;

    @Resource
    private SubjectService subjectService;


    @PostMapping("/upload")
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file,
                                       @RequestParam("subjectId") Integer subjectId,
                                       @RequestParam("bankTitle") String bankTitle) throws IOException {
        bankService.processCsvFile(file, subjectId, bankTitle);
        return ResultUtils.success(IMPORT_BANK_SUCCESS);
    }

    /**
     * 题库分页查询
     * @param page
     * @param size
     * @param bankTitle
     * @return
     */
    @GetMapping("/page")
    public BaseResponse<Page> page(Integer page, Integer size, String bankTitle) {
        Page<Bank> bankPage = new Page<>();
        LambdaQueryWrapper<Bank> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(bankTitle != null, Bank::getBankTitle, bankTitle)
                .orderByAsc(Bank::getBankId);
        bankService.page(bankPage);

        Page<BankDto> bankDtoPage = new Page<>();
        BeanUtils.copyProperties(bankPage, bankDtoPage);
        List<Bank> records = bankPage.getRecords();

        List<BankDto> bankDtoList = records.parallelStream().map(bank -> {
            BankDto bankDto = new BankDto();
            bankDto.setBankId(bank.getBankId());
            bankDto.setBankTitle(bank.getBankTitle());
            LambdaQueryWrapper<Subject> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Subject::getSubjectId, bank.getSubjectId());
            Subject subject = subjectService.getOne(queryWrapper);
            bankDto.setSubjectName(subject.getSubjectName());
            return bankDto;
        }).collect(Collectors.toList());
        bankDtoPage.setRecords(bankDtoList);
        return ResultUtils.success(bankDtoPage);
    }

}
