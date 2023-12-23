package me.ijusthaveto.exam.domain.dto;

import lombok.Data;
import me.ijusthaveto.exam.domain.Bank;

@Data
public class BankDto extends Bank {
    private String subjectName;
    private Integer singleNum;
    private Integer multipleNum;
    private Integer judgeNum;
}
