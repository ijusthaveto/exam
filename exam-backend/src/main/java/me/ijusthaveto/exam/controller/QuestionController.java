package me.ijusthaveto.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import me.ijusthaveto.exam.common.BaseResponse;
import me.ijusthaveto.exam.common.ErrorCode;
import me.ijusthaveto.exam.common.ResultUtils;
import me.ijusthaveto.exam.constant.ResultConstant;
import me.ijusthaveto.exam.domain.Question;
import me.ijusthaveto.exam.domain.dto.BankDto;
import me.ijusthaveto.exam.domain.dto.QuestionDetail;
import me.ijusthaveto.exam.domain.dto.QuestionDto;
import me.ijusthaveto.exam.domain.dto.QuestionPageDto;
import me.ijusthaveto.exam.service.ExamquestionService;
import me.ijusthaveto.exam.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static me.ijusthaveto.exam.constant.ResultConstant.MODIFY_BANK_INFO_SUCCESS;
import static me.ijusthaveto.exam.constant.ResultConstant.MODIFY_QUESTION_INFO_SUCCESS;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/info/{questionId}")
    public BaseResponse<QuestionDetail> getQuestionDetailById(@PathVariable Integer questionId) {
        return ResultUtils.success(questionService.getQuestionDetailById(questionId));
    }

    /**
     * 题目分页查询
     * @param dto
     * @return
     */
    @PostMapping("/page")
    public BaseResponse<Page> page(@RequestBody QuestionPageDto dto) {
        Page<QuestionDetail> questionDetailPage = questionService.selectPage(dto.getPage(), dto.getSize(), dto.getQuestionType());
        return ResultUtils.success(questionDetailPage);
    }

    /**
     * 添加题目
     * @param question
     * @return
     */
    @PostMapping("/add")
    public BaseResponse save(@RequestBody Question question) {
        boolean saveResult = questionService.save(question);
        if (saveResult) {
            return ResultUtils.success(ResultConstant.ADD_QUESTION_SUCCESS);
        }
        return ResultUtils.error(ErrorCode.QUESTION_ADD_ERROR);
    }

    @PutMapping
    public BaseResponse<String> modifyQuestionInfo(@RequestBody QuestionDetail dto) {
        questionService.modifyQuestionInfo(dto);
        return ResultUtils.success(MODIFY_QUESTION_INFO_SUCCESS);
    }

    /**
     * 删除题目
     * @param questionId
     * @return
     */
    @DeleteMapping("/{questionId}")
    public BaseResponse delete(@PathVariable Integer questionId) {
        questionService.deleteQuestion(questionId);
        return ResultUtils.success(ResultConstant.REMOVE_QUESTION_SUCCESS);
    }

    private Page<Question> buildPage(Integer page, Integer size) {
        return new Page<>(page, size);
    }

    private LambdaQueryWrapper<Question> buildQueryWrapper(QuestionPageDto dto) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();

        if (dto.getSubjectId() != null) {
            wrapper.eq(Question::getSubjectId, dto.getSubjectId());
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
