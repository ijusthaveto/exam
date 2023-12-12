package me.ijusthaveto.exam.service.impl;

import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ExceptionUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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


}
