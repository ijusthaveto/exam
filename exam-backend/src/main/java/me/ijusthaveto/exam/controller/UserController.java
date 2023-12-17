package me.ijusthaveto.exam.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.User;
import me.ijusthaveto.exam.domain.dto.UserLoginDto;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<User> register(@RequestBody UserRegisterDto dto) {

        userService.register(dto);
        return ResultUtils.success(null);
    }

    @PostMapping("/login")
    public BaseResponse<SaTokenInfo> login(@RequestBody UserLoginDto dto) {
        userService.login(dto);
        return ResultUtils.success(StpUtil.getTokenInfo());
    }

    @PostMapping("/logout")
    public BaseResponse logout() {
        StpUtil.logout(StpUtil.getLoginId());
        return ResultUtils.success(null);
    }



}
