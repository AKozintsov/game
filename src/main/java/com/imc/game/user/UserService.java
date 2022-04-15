package com.imc.game.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public User register(UserModel userModel) {
        return userRepository.save(userMapper.toUser(userModel));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @PostConstruct
    void initUsers() {
        initUser("admin1", "admin1");
        initUser("admin2", "admin2");
    }

    private void initUser(String username, String password) {
        if (!isExistsByUsername(username))
            userRepository.save(userMapper.toUser(UserModel.builder()
                    .username(username)
                    .password(password)
                    .build()));
    }
}
