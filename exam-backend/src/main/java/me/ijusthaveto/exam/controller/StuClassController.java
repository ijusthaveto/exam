package me.ijusthaveto.exam.controller;

import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Class;
import me.ijusthaveto.exam.mapper.ClassMapper;
import me.ijusthaveto.exam.service.ClassService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ijusthaveto
 * @create 2023-12-17
 */
@RestController
@CrossOrigin
@RequestMapping("/class")
public class StuClassController {

    @Resource
    private ClassService classService;

    @GetMapping("/list")
    public BaseResponse<List<Class>> list() {
        List<Class> list = classService.list();
        return ResultUtils.success(list);
    }

}
