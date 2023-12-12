package me.ijusthaveto.exam.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.domain.Admin;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.mapper.AdminMapper;
import me.ijusthaveto.exam.service.AdminService;
import org.springframework.stereotype.Service;

/**
* @author 修雯天
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-12-12 15:44:33
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Override
    public void login(String secretCode) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getSecretCode, secretCode);
        Long count = baseMapper.selectCount(wrapper);
        if (count != 1) {
            throw new BusinessException(ErrorCode.ADMIN_NOT_EXISTS);
        }
        Admin admin = baseMapper.selectOne(wrapper);
        StpUtil.login(admin.getAdminId());
    }
}




