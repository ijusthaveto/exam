package me.ijusthaveto.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.ijusthaveto.exam.domain.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import me.ijusthaveto.exam.domain.dto.QuestionDetail;
import me.ijusthaveto.exam.domain.dto.QuestionDto;
import me.ijusthaveto.exam.domain.dto.QuestionPageDto;

import java.util.List;

/**
* @author 修雯天
* @description 针对表【question】的数据库操作Service
* @createDate 2023-12-10 13:14:24
*/
public interface QuestionService extends IService<Question> {

    /**
     * 根据题目ID删除题目，同时移除在考试关联中的记录
     * @param questionId
     */
    void deleteQuestion(Integer questionId);

    /**
     * 随机挑选题目
     * @param bankId
     * @param singleNum
     * @param multipleNum
     * @param boolNum
     * @return
     */
    List<Question> pickQuestion(Integer bankId,
                                   Integer singleNum,
                                   Integer multipleNum,
                                   Integer boolNum);

    /**
     * 查询题库中题目个数
     * @param bankId
     * @return
     */
    Integer selectQuestionNum(Integer bankId, String type);

    Page<QuestionDetail> selectPage(int page, int size, String type);

    QuestionDetail getQuestionDetailById(Integer questionId);

    void modifyQuestionInfo(QuestionDetail dto);
}
