package com.imc.game.playerchoice;

import com.imc.game.round.RoundService;
import com.imc.game.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerChoiceService {

    private final UserService userService;

    private final RoundService roundService;

    private final PlayerChoiceRepository playerChoiceRepository;

    private final PlayerChoiceMapper playerChoiceMapper;

    public PlayerChoice processPlayerChoice(String userName, String inviteCode, String choice) {

        var round = roundService.getRoundByInviteCode(inviteCode);
        var user = userService.getUserByUsername(userName);
        return playerChoiceRepository.save(playerChoiceMapper.toPlayerChoice(round, user, choice));
    }
}
