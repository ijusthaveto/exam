package me.ijusthaveto.exam.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class AdminServiceImplTest {

    @Resource
    private AdminService adminService;

    @Test
    void testAdminLoginFailed() {
        Assertions.assertThrows(BusinessException.class, () -> {
            final String secretCode = UUID.randomUUID().toString();
            adminService.login(secretCode);
        });
    }

    @Test
    void testAdminLoginSuccess() {
        final String secretCode = "fcb55c05-e134-4660-b10d-46eaf7ff069f";
        adminService.login(secretCode);
        Object loginId = StpUtil.getLoginId();
        Assertions.assertNotNull(loginId);
    }


}
