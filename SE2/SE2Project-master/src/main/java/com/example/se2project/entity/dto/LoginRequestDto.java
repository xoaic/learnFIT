package com.example.se2project.entity.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    @NotBlank(message = "Email can not blank!")
    @Email(message = "This is not email format!")
    private String email;

    @NotBlank
    @Length(min = 5, max = 30, message = "Password must be <5 and >30")
    private String password;
}
