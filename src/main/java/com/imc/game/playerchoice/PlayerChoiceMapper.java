package com.imc.game.playerchoice;

import com.imc.game.round.Round;
import com.imc.game.user.User;
import org.springframework.stereotype.Component;

@Component
public class PlayerChoiceMapper {

    PlayerChoice toPlayerChoice(Round round, User user, String choice) {
        return PlayerChoice.builder()
                .round(round)
                .user(user)
                .choice(Choice.valueOf(choice))
                .build();
    }
}
