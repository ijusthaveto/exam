package me.ijusthaveto.exam.controller;

import lombok.extern.slf4j.Slf4j;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Class;
import me.ijusthaveto.exam.service.ClassService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ijusthaveto
 * @create 2023-12-22
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/clazz")
public class ClazzController {

    @Resource
    private ClassService classService;

    /**
     * 获取所有班级信息
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<Class>> list() {
        return ResultUtils.success(classService.list());
    }
}
