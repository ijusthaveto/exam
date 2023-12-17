package me.ijusthaveto.exam.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.User;
import me.ijusthaveto.exam.domain.dto.StuDto;
import me.ijusthaveto.exam.domain.dto.UserLoginDto;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;
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

    /**
     * 学生分页查询
     * @param page
     * @param size
     * @param username
     * @return
     */
    @GetMapping("/page")
    public BaseResponse<Page> page(Integer page, Integer size, String username) {
        Page<StuDto> stuDtoPage = userService.selectPage(page, size, username);
        return ResultUtils.success(stuDtoPage);
    }
}
