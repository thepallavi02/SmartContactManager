package com.project.scm.Forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class UserRegisterForm {
    @NotBlank(message = "User Name is required")
    @Size(min=2,message = "Min 2 characters are required")
    private String name;
    @NotBlank(message="Invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min=4,message = "Min 4 characters are required")
    private String password;
    @Size(min=8,max=12,message = "Invalid phone numbers")
    private String phone;
    private String about;
}
