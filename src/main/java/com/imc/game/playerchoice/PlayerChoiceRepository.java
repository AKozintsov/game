package com.imc.game.playerchoice;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlayerChoiceRepository extends CrudRepository<PlayerChoice, UUID> {
}
