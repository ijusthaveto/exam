package me.ijusthaveto.exam.service;

import me.ijusthaveto.exam.domain.Question;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
