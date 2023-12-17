package me.ijusthaveto.exam.domain.dto;

import lombok.Data;
import me.ijusthaveto.exam.domain.User;

@Data
public class StuDto extends User {
    private String classNo;
}
