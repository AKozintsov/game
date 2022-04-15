package com.imc.game.gameplay;

import com.imc.game.playerchoice.Choice;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.util.stream.Stream;

import static com.imc.game.TestData.createParticipants;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GamePlayServiceTest {

    @InjectMocks
    private GamePlayService gamePlayService;

    @ParameterizedTest
    @MethodSource("provideOptions")
    public void decideWinnerTest(Choice firstChoice, Choice secondChoice, Pair<Boolean, Boolean> result) {
        var participants = createParticipants();

        var firstParticipant = participants.get(0);
        firstParticipant.setChoice(firstChoice);

        var secondParticipant = participants.get(1);
        secondParticipant.setChoice(secondChoice);

        gamePlayService.decideWinner(participants);

        assertThat(firstParticipant.getIsWinner()).isEqualTo(result.getFirst());
        assertThat(secondParticipant.getIsWinner()).isEqualTo(result.getSecond());
    }

    private static Stream<Arguments> provideOptions() {
        return Stream.of(
                Arguments.of(Choice.PAPER, Choice.PAPER, Pair.of(false, false)),
                Arguments.of(Choice.SCISSORS, Choice.SCISSORS, Pair.of(false, false)),
                Arguments.of(Choice.ROCK, Choice.ROCK, Pair.of(false, false)),
                Arguments.of(Choice.PAPER, Choice.SCISSORS, Pair.of(false, true)),
                Arguments.of(Choice.PAPER, Choice.ROCK, Pair.of(true, false)),
                Arguments.of(Choice.ROCK, Choice.SCISSORS, Pair.of(true, false)),
                Arguments.of(Choice.ROCK, Choice.PAPER, Pair.of(false, true)));

    }





}
