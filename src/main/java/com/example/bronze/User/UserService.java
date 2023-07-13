package com.example.bronze.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(String userId, String password, String Email){
        SiteUser user = new SiteUser();
        user.setUserId(userId);
        user.setPassword(password);
        user.setEmail(Email);
        this.userRepository.save(user);
    }
}
