package me.ijusthaveto.exam.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class CalcDto {
    private List<QuestionDto> single;
    private List<QuestionDto> multiple;
    private List<QuestionDto> judge;
    private Integer examId;
}
