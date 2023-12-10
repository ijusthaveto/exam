package me.ijusthaveto.exam.domain.dto;

import lombok.Data;

@Data
public class QuestionPageDto {
    private int page = 1;
    private int size = 10;
    private Long questionBankId;
    private String questionType;
    private String difficultyLevel;
    private String keyword;
}
