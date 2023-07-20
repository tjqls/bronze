package com.example.bronze.User;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserCreateForm {

    @NotEmpty(message="아이디는 필수 입력값입니다.")
    private String username;

    @NotEmpty(message="비밀번호는 필수 입력값입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수 입력값입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Email
    private String Email;
}
