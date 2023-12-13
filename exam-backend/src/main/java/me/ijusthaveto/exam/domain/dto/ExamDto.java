package me.ijusthaveto.exam.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.ijusthaveto.exam.domain.Exam;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamDto extends Exam {
    /**
     * 班级ID
     */
    private Integer classId;
    /**
     * 班级号
     */
    private String classNo;
    /**
     * 考试开始时间——结束时间
     */
    private String examStartToEnd;
    /**
     * 参与考试人数
     */
    private Integer examPeopleNum;
}
