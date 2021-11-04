package com.budev.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResetPasswordDto {

    private int userId;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;


}
