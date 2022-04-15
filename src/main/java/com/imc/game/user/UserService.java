package com.imc.game.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
