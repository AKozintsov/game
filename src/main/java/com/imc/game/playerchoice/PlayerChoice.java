package com.imc.game.playerchoice;

import com.imc.game.round.Round;
import com.imc.game.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "player_choices")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerChoice {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Enumerated(EnumType.STRING)
    private Choice choice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Round round;

}
