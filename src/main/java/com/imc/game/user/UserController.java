package com.imc.game.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RegistrationValidator registrationValidator;

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:/";
        }
        model.addAttribute(UserModel.builder().build());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistration(UserModel userModel, BindingResult bindingResult) {
        registrationValidator.validate(userModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.register(userModel);
        return "redirect:/login";
    }

}
