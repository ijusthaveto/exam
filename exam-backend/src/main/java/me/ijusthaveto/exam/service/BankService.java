package me.ijusthaveto.exam.service;

import me.ijusthaveto.exam.domain.Bank;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author 修雯天
* @description 针对表【bank】的数据库操作Service
* @createDate 2023-12-17 18:20:22
*/
public interface BankService extends IService<Bank> {

    void processCsvFile(MultipartFile file, Integer subjectId, String bankTitle) throws IOException;
}
