package com.imc.game;

import com.imc.game.gameplay.ParticipantModel;

import java.util.List;

public class TestData {

    public static List<ParticipantModel> createParticipants() {
        return List.of(
                ParticipantModel.builder()
                        .username("user1")
                        .isWinner(false)
                        .build(),
                ParticipantModel.builder()
                        .username("user2")
                        .isWinner(false)
                        .build());
    }

}
