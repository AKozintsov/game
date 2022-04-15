package com.imc.game.round;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoundRepository extends CrudRepository<Round, UUID> {

    Round findByInviteCode(String inviteCode);
}
