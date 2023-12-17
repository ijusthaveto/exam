package me.ijusthaveto.exam.controller;

import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Bank;
import me.ijusthaveto.exam.service.BankService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.IOException;

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


    @PostMapping("/upload")
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file,
                                       @RequestParam("subjectId") Integer subjectId,
                                       @RequestParam("bankTitle") String bankTitle) throws IOException {
        bankService.processCsvFile(file, subjectId, bankTitle);
        return ResultUtils.success(IMPORT_BANK_SUCCESS);
    }

}
