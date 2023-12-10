package me.ijusthaveto.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.QuestionPageDto;
import me.ijusthaveto.exam.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/page")
    public BaseResponse<Page> page(@RequestBody QuestionPageDto dto) {
        Page<Question> page = buildPage(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Question> wrapper = buildQueryWrapper(dto);

        return ResultUtils.success(questionService.page(page, wrapper));
    }

    private Page<Question> buildPage(Integer page, Integer size) {
        return new Page<>(page, size);
    }

    private LambdaQueryWrapper<Question> buildQueryWrapper(QuestionPageDto dto) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();

        if (dto.getQuestionBankId() != null) {
            wrapper.eq(Question::getQuestionBankId, dto.getQuestionBankId());
        }
        if (!StringUtils.isNullOrEmpty(dto.getQuestionType())) {
            wrapper.eq(Question::getQuestionType, dto.getQuestionType());
        }
        if (!StringUtils.isNullOrEmpty(dto.getDifficultyLevel())) {
            wrapper.eq(Question::getDifficultyLevel, dto.getDifficultyLevel());
        }
        if (!StringUtils.isNullOrEmpty(dto.getKeyword())) {
            wrapper.like(Question::getQuestionContent, dto.getKeyword()).or().like(Question::getOptions, dto.getKeyword());
        }
        return wrapper;
    }
}
