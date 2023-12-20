package me.ijusthaveto.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.ijusthaveto.exam.domain.User;
import me.ijusthaveto.exam.domain.dto.StuDto;
import me.ijusthaveto.exam.domain.dto.UserLoginDto;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 修雯天
* @description 针对表【user】的数据库操作Service
* @createDate 2023-12-09 23:20:17
*/
public interface UserService extends IService<User> {

    void register(UserRegisterDto dto);

    void login(UserLoginDto dto);

    Page<StuDto> selectPage(Integer page, Integer size, String userNo);

    void processCsvFile(MultipartFile file, String classNo);
}
