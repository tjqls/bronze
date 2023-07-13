package com.example.bronze.User;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.bridge.Message;

@Getter
@Setter
public class User_Create_Form {

    @NotEmpty(message="아이디는 필수 입력값입니다.")
    private String userId;

    @NotEmpty(message="비밀번호는 필수 입력값입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수 입력값입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Email
    private String Email;
}
