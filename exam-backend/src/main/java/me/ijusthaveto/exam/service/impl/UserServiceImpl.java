package me.ijusthaveto.exam.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.ijusthaveto.exam.domain.User;
import me.ijusthaveto.exam.domain.dto.UserLoginDto;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.UserService;
import me.ijusthaveto.exam.mapper.UserMapper;
import org.springframework.stereotype.Service;

import static me.ijusthaveto.exam.common.ErrorCode.*;
import static me.ijusthaveto.exam.constant.RoleConstant.DEFAULT_ROLE;

/**
* @author 修雯天
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-12-09 23:20:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public void register(UserRegisterDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String conform = dto.getConfirmPassword();

        if (!conform.equals(password)) {
            throw new BusinessException(CONFIRM_ERROR);
        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        Long count = baseMapper.selectCount(wrapper);
        if (count > 1) {
            throw new BusinessException(USERNAME_ERROR);
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(password);
        user.setRoleId(DEFAULT_ROLE);

        int insert = baseMapper.insert(user);
        if (insert != 1) {
            throw new BusinessException(REGISTER_ERROR);
        }
    }

    @Override
    public void login(UserLoginDto dto) {
        String password = dto.getPassword();
        String username = dto.getUsername();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        Long count = baseMapper.selectCount(wrapper);
        if (count != 1) {
            throw new BusinessException(USER_NOT_EXIST_ERROR);
        }
        User user = baseMapper.selectOne(wrapper);
        if (!user.getPasswordHash().equals(password)) {
            throw new BusinessException(LOGIN_ERROR);
        }

        StpUtil.login(user.getUserId());
    }
}




