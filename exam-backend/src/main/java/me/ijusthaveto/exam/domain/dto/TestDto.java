package me.ijusthaveto.exam.domain.dto;

import lombok.Data;

/**
 * @author ijusthaveto
 * @create 2023-12-22
 */
@Data
public class TestDto {
    private String examTitle;
    private String startTime;
    private String endTime;
    private Integer classId;
    private Integer bankId;
    private Integer singleNum;
    private Double singleScore;
    private Integer multipleNum;
    private Double multipleScore;
    private Integer boolNum;
    private Double boolScore;
}
