package me.ijusthaveto.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.constant.ResultConstant;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.QuestionPageDto;
import me.ijusthaveto.exam.service.ExamquestionService;
import me.ijusthaveto.exam.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    /**
     * 题目分页查询
     * @param dto
     * @return
     */
    @GetMapping("/page")
    public BaseResponse<Page> page(@RequestBody QuestionPageDto dto) {
        Page<Question> page = buildPage(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Question> wrapper = buildQueryWrapper(dto);

        return ResultUtils.success(questionService.page(page, wrapper));
    }

    @PostMapping("/add")
    public BaseResponse save(@RequestBody Question question) {
        boolean saveResult = questionService.save(question);
        if (saveResult) {
            return ResultUtils.success(ResultConstant.ADD_QUESTION_SUCCESS);
        }
        return ResultUtils.error(ErrorCode.QUESTION_ADD_ERROR);
    }

    @DeleteMapping("/delete/{questionId}")
    public BaseResponse delete(@PathVariable Integer questionId) {
        questionService.deleteQuestion(questionId);
        return ResultUtils.success(ResultConstant.REMOVE_QUESTION_SUCCESS);
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
