package me.ijusthaveto.exam.controller;

import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.User;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<User> register(@RequestBody UserRegisterDto dto) {
        if (dto == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        userService.register(dto);
        return ResultUtils.success(null);
    }
}
