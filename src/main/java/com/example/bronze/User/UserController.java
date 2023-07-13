package com.example.bronze.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/signup")
    public String signup(User_Create_Form user_create_form){
        return "signup_form";
    }

    @PostMapping("/user/signup")
    public String signup(@Valid User_Create_Form user_create_form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }
        if(!user_create_form.getPassword().equals(user_create_form.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        userService.signup(user_create_form.getUserId(), user_create_form.getPassword(), user_create_form.getEmail());
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String login(){
        return "login_form";
    }


}
