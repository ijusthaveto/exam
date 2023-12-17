package me.ijusthaveto.exam.controller;

import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Subject;
import me.ijusthaveto.exam.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static me.ijusthaveto.exam.constant.ResultConstant.ADD_SUBJECT_SUCCESS;

/**
 * @author ijusthaveto
 * @create 2023-12-17
 */
@RestController
@CrossOrigin
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    /**
     * 添加学科
     * @param subject
     * @return
     */
    @PostMapping("/save")
    public BaseResponse<String> save(@RequestBody Subject subject) {
        subjectService.save(subject);
        return ResultUtils.success(ADD_SUBJECT_SUCCESS);
    }
}
