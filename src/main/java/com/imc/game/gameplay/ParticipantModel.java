package com.imc.game.gameplay;

import com.imc.game.playerchoice.Choice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantModel {

    private String username;

    private Choice choice;

    private Boolean isWinner;
}
