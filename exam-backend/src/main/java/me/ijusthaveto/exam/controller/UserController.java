package me.ijusthaveto.exam.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.User;
import me.ijusthaveto.exam.domain.dto.History;
import me.ijusthaveto.exam.domain.dto.StuDto;
import me.ijusthaveto.exam.domain.dto.UserLoginDto;
import me.ijusthaveto.exam.domain.dto.UserRegisterDto;
import me.ijusthaveto.exam.exception.BusinessException;
import me.ijusthaveto.exam.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.util.List;

import static me.ijusthaveto.exam.common.ErrorCode.REMOVE_USER_ERROR;
import static me.ijusthaveto.exam.constant.ResultConstant.*;

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

    @GetMapping("/{userId}")
    public BaseResponse<StuDto> getUserById(@PathVariable("userId") Integer userId) {
        StuDto user = userService.getUserById(userId);
        return ResultUtils.success(user);
    }

    @PutMapping
    public BaseResponse<String> modifyUserInfo(@RequestBody StuDto dto) {
        userService.modifyUserInfo(dto);
        return ResultUtils.success(MODIFY_USER_INFO_SUCCESS);
    }

    @GetMapping("/list")
    public BaseResponse<List<User>> list() {
        return ResultUtils.success(userService.list());
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
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/remove/{userId}")
    public BaseResponse<String> removeById(@PathVariable("userId") Integer userId) {
        boolean success = userService.removeById(userId);
        if (!success) {
            throw new BusinessException(REMOVE_USER_ERROR);
        }
        return ResultUtils.success(REMOVE_USER_SUCCESS);

    }

    @GetMapping("/history")
    public BaseResponse<List<History>> page() {
        List<History> historyPage = userService.selectHistoryScore();
        return ResultUtils.success(historyPage);
    }

    /**
     * 学生分页查询
     * @param page
     * @param size
     * @param userNo
     * @return
     */
    @GetMapping("/page")
    public BaseResponse<Page> page(Integer page, Integer size, String userNo) {
        Page<StuDto> stuDtoPage = userService.selectPage(page, size, userNo);
        return ResultUtils.success(stuDtoPage);
    }

    /**
     * 批量导入学生信息
     * @param file
     * @param classNo
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file,
                                       @RequestParam("classNo") String classNo) {
        userService.processCsvFile(file, classNo);
        return ResultUtils.success(IMPORT_USER_SUCCESS);
    }


}
