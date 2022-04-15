package com.imc.game.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User toUser(UserModel userModel) {
        return User.builder()
                .username(userModel.getUsername())
                .password(bCryptPasswordEncoder.encode(userModel.getPassword()))
                .build();
    }

}
