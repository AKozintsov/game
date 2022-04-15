package com.imc.game.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RegistrationValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        var userModel = (UserModel) o;
        if (userService.isExistsByUsername(userModel.getUsername())) {
                errors.reject("username", "username already exists");
        }
    }
}
