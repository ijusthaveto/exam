package me.ijusthaveto.exam.domain.dto;

import lombok.Data;
import me.ijusthaveto.exam.domain.Task;

import java.util.List;

/**
 * @author ijusthaveto
 * @create 2023-12-18
 */
@Data
public class TaskDto extends Task {
    private String userNo;
    private String classNo;
    private String username;
    private String subjectName;
    private String bankTitle;
    private List<Integer> classIdList;
    private List<QuestionDto> questionDtoList;
    private String examTitle;
}
