package me.ijusthaveto.exam.domain.dto;

import lombok.Data;
import me.ijusthaveto.exam.domain.Question;

/**
 * @author ijusthaveto
 * @create 2023-12-18
 */
@Data
public class QuestionDto extends Question {
    private String bankTitle;
    private String questionAnswerList;
}
