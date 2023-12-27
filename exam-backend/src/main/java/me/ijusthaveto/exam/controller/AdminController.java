package me.ijusthaveto.exam.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Admin;
import me.ijusthaveto.exam.domain.dto.AdminLoginDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

import static me.ijusthaveto.exam.constant.ResultConstant.VALID_ADMIN_IDENTITY;

/**
 * @author ijusthaveto
 * @create 2023-12-22
 */
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

    @GetMapping("/{loginId}")
    public BaseResponse<String> judgeAdminIdentity(@PathVariable("loginId") Integer loginId) {
        Admin admin = adminService.getById(loginId);
        if (Objects.isNull(admin)) {
            throw new BusinessException(ErrorCode.MISS_ADMIN_IDENTITY);
        }
        return ResultUtils.success(VALID_ADMIN_IDENTITY);
    }
}
