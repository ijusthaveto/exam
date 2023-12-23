package me.ijusthaveto.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Bank;
import me.ijusthaveto.exam.domain.Exam;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.BankDto;
import me.ijusthaveto.exam.service.BankService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import static me.ijusthaveto.exam.constant.ResultConstant.*;

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

    @PostMapping("/upload")
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file,
                                       @RequestParam("subjectId") Integer subjectId,
                                       @RequestParam("bankTitle") String bankTitle) throws IOException {
        bankService.processCsvFile(file, subjectId, bankTitle);
        return ResultUtils.success(IMPORT_BANK_SUCCESS);
    }



    /**
     * 题库分页查询
     *
     * @param page
     * @param size
     * @param bankTitle
     * @return
     */
    @GetMapping("/page")
    public BaseResponse<Page> page(Integer page, Integer size, String bankTitle) {
        Page<BankDto> resultPage = bankService.selectPage(page, size, bankTitle);
        return ResultUtils.success(resultPage);
    }

    /**
     * 获取题库列表
     * @param subjectId
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<Bank>> list(@RequestParam("subjectId") Integer subjectId) {
        List<Bank> list = bankService.selectAllList(subjectId);
        return ResultUtils.success(list);
    }

    @GetMapping("/all")
    public BaseResponse<List<Bank>> getBankList() {
        return ResultUtils.success(bankService.list());
    }

    /**
     * 删除题库并删除其相关的题目
     * @param bankId
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse<String> delete(@RequestParam("bankId") Integer bankId) {
        bankService.deleteWithQuestion(bankId);
        return ResultUtils.success(REMOVE_BANK_SUCCESS);
    }

    @GetMapping("/{bankId}")
    public BaseResponse<BankDto> getBankById(@PathVariable Integer bankId) {
        return ResultUtils.success(bankService.getBankDtoById(bankId));
    }

    @PutMapping
    public BaseResponse<String> modifyBankInfo(@RequestBody BankDto dto) {
        bankService.modifyBankInfo(dto);
        return ResultUtils.success(MODIFY_BANK_INFO_SUCCESS);
    }


}
