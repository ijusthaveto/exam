package me.ijusthaveto.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.ijusthaveto.exam.domain.Bank;
import com.baomidou.mybatisplus.extension.service.IService;
import me.ijusthaveto.exam.domain.dto.BankDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* @author 修雯天
* @description 针对表【bank】的数据库操作Service
* @createDate 2023-12-17 18:20:22
*/
public interface BankService extends IService<Bank> {

    void processCsvFile(MultipartFile file, Integer subjectId, String bankTitle) throws IOException;

    Page<BankDto> selectPage(Integer page, Integer size, String bankTitle);

    List<Bank> selectAllList(Integer subjectId);

    void deleteWithQuestion(Integer bankId);

    BankDto getBankDtoById(Integer bankId);

    void modifyBankInfo(BankDto dto);
}
