package com.imc.game.gameplay;

import com.imc.game.playerchoice.Choice;
import com.imc.game.round.Round;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GamePlayService {

    public List<ParticipantModel> getGameResult(Round round) {
        var participants = round.getPlayerChoices().stream()
                .map(playerChoice -> ParticipantModel.builder()
                        .username(playerChoice.getUser().getUsername())
                        .choice(playerChoice.getChoice())
                        .isWinner(false)
                        .build())
                .collect(Collectors.toList());

        if (participants.size() == 2) {
            decideWinner(participants);
        }
        return participants;
    }

    public void decideWinner(List<ParticipantModel> participants) {
        var firstParticipant = participants.get(0);
        var firstChoice = firstParticipant.getChoice();
        log.info("First participant {} choose {}", firstParticipant.getUsername(), firstChoice);

        var secondParticipant = participants.get(1);
        var secondChoice = secondParticipant.getChoice();
        log.info("Second participant {} choose {}", secondParticipant.getUsername(), secondChoice);

        if (firstChoice.equals(secondChoice)) {
            participants.forEach(participant -> participant.setIsWinner(false));
            return;
        }

        switch (firstChoice) {
            case ROCK:
                if (secondChoice.equals(Choice.PAPER)) {
                    secondParticipant.setIsWinner(true);
                } else {
                    firstParticipant.setIsWinner(true);
                }
                break;
            case PAPER:
                if (secondChoice.equals(Choice.ROCK)) {
                    firstParticipant.setIsWinner(true);
                } else {
                    secondParticipant.setIsWinner(true);
                }
                break;
            case SCISSORS:
                if (secondChoice.equals(Choice.PAPER)) {
                    firstParticipant.setIsWinner(true);
                } else {
                    secondParticipant.setIsWinner(true);
                }
                break;
        }
    }
}
