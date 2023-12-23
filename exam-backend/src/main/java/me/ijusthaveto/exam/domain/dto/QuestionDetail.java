package me.ijusthaveto.exam.domain.dto;

import lombok.Data;
import me.ijusthaveto.exam.domain.Question;

@Data
public class QuestionDetail extends Question {
    private String subjectName;
    private String bankTitle;
}
