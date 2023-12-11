package me.ijusthaveto.exam.mapper;

import me.ijusthaveto.exam.domain.Examquestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 修雯天
* @description 针对表【examquestion】的数据库操作Mapper
* @createDate 2023-12-11 12:42:47
* @Entity me.ijusthaveto.exam.domain.Examquestion
*/
public interface ExamquestionMapper extends BaseMapper<Examquestion> {

    /**
     * 根据考试ID查询题目ID列表
     * @param examId
     * @return
     */
    @Select("select eq.questionId\n" +
            "from examquestion eq\n" +
            "where eq.examId = #{examId}\n")
    public List<Integer> getQuestionIdsByExamId(@Param("examId") Integer examId);

}




