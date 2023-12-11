package me.ijusthaveto.exam.mapper;

import me.ijusthaveto.exam.domain.Studentexam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 修雯天
* @description 针对表【studentexam】的数据库操作Mapper
* @createDate 2023-12-11 12:42:55
* @Entity me.ijusthaveto.exam.domain.Studentexam
*/
public interface StudentexamMapper extends BaseMapper<Studentexam> {

    /**
     * 根据用户ID查询关联的所有考试Id
     * @param userId
     * @return
     */
    @Select("select se.examId\n" +
            "from user u\n" +
            "join studentexam se on u.userId = se.userId\n" +
            "where u.userId = #{userId}")
    public List<Integer> getExamIdsByUserId(@Param("userId") Integer userId);

}




