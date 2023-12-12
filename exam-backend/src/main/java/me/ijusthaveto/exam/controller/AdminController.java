package me.ijusthaveto.exam.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.dto.AdminLoginDto;
import me.ijusthaveto.exam.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public BaseResponse<SaTokenInfo> login(@RequestBody AdminLoginDto dto) {
        adminService.login(dto.getSecretCode());
        return ResultUtils.success(StpUtil.getTokenInfo());
    }
}
