package me.ijusthaveto.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.ijusthaveto.exam.domain.User;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;
import me.ijusthaveto.exam.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    void register() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("mightcell");
        dto.setPassword("pwd123");
        dto.setConfirmPassword("pwd123");

        userService.register(dto);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User one = userService.getOne(wrapper);
        System.out.println(one.toString());
    }

    @Test
    void generateAdminCode() {
        System.out.println(UUID.randomUUID().toString());
    }
}
