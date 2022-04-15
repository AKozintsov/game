package com.imc.game.gameplay;

import com.imc.game.playerchoice.Choice;
import com.imc.game.playerchoice.PlayerChoiceService;
import com.imc.game.round.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class GamePlayController {

    private final PlayerChoiceService playerChoiceService;

    private final GamePlayService gamePlayService;

    private final RoundService roundService;

    @GetMapping("/game-room")
    public String gameRoomPage() {
        return "game-room";
    }

    @GetMapping("/gameplay")
    public String gameplayPage(Model model, String inviteCode) {
        model.addAttribute("inviteCode", inviteCode);
        model.addAttribute("choices", Arrays.asList(Choice.values()));
        return "gameplay";
    }

    @PostMapping("/gameplay/choice")
    public String processChoice(Principal principal, String choice, String inviteCode, RedirectAttributes redirectAttributes) {
        playerChoiceService.processPlayerChoice(principal.getName(), inviteCode, choice);
        redirectAttributes.addAttribute("inviteCode", inviteCode);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String getRoundResult(@RequestParam String inviteCode, Model model) {
        var round = roundService.getRoundByInviteCode(inviteCode);
        if (round.getPlayerChoices().size() < 2) {
            model.addAttribute("awaitMessage", "wait for all players");
            return "result";
        }

        var participants = gamePlayService.getGameResult(round);

        if (participants.size() == 2) {
            round.setIsActive(false);
            roundService.saveRound(round);
        }

        model.addAttribute(participants);
        return "result";
    }

}
