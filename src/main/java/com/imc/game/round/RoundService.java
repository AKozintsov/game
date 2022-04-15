package com.imc.game.round;

import com.imc.game.user.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final UserService userService;
    
    private final RoundRepository roundRepository;

    public Round getRoundByInviteCode(String inviteCode) {
        return roundRepository.findByInviteCode(inviteCode);
    }

    public Round createRound() {
        return roundRepository.save(createInitRound());
    }

    public Round joinRound(String username, String inviteCode) {
        var user = userService.getUserByUsername(username);
        var round = roundRepository.findByInviteCode(inviteCode);
        round.getUsers().add(user);
        return roundRepository.save(round);
    }

    public Round saveRound(Round round) {
        return roundRepository.save(round);
    }

    private Round createInitRound() {
        var inviteCode = RandomString.make(5);
        return Round.builder()
                .inviteCode(inviteCode)
                .isActive(true)
                .build();
    }

}
