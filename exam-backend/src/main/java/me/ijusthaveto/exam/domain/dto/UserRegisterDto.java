package me.ijusthaveto.exam.domain.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String confirmPassword;
}
