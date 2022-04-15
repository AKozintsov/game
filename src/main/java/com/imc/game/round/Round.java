package com.imc.game.round;

import com.imc.game.playerchoice.PlayerChoice;
import com.imc.game.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "rounds")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"playerChoices"})
@ToString(exclude = {"playerChoices"})
public class Round {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users;

    private String inviteCode;

    private Boolean isActive;

    @OneToMany(mappedBy = "round",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PlayerChoice> playerChoices;

}
