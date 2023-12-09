package me.ijusthaveto.exam.service;

import me.ijusthaveto.exam.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;

/**
* @author 修雯天
* @description 针对表【user】的数据库操作Service
* @createDate 2023-12-09 23:20:17
*/
public interface UserService extends IService<User> {

    void register(UserRegisterDto dto);
}
