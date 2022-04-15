package com.imc.game.round;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;

    @GetMapping("/round")
    public String roundPage() {
        return "round";
    }

    @PostMapping("/round")
    public String createRound(Model model) {
        var round = roundService.createRound();
        model.addAttribute("inviteCode", round.getInviteCode());
        return "round";
    }

    @PostMapping("/round/join")
    public String joinRound(String inviteCode, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("inviteCode", inviteCode);
        return "redirect:/gameplay";
    }
}
