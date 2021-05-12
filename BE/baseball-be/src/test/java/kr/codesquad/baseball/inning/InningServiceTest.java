package kr.codesquad.baseball.inning;

import kr.codesquad.baseball.game.service.GameService;
import kr.codesquad.baseball.inning.domain.InningRepository;
import kr.codesquad.baseball.player.domain.PlayerRepository;
import kr.codesquad.baseball.team.domain.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InningServiceTest {

    @Mock
    InningRepository inningRepository;

    @Mock
    TeamRepository teamRepository;

    @Mock
    GameService gameService;

    @Mock
    PlayerRepository playerRepository;

    InningService inningService;

    @BeforeEach
    void setUp() {
        inningService = new InningService(inningRepository, teamRepository, gameService, playerRepository);
    }

    @ParameterizedTest
    @MethodSource("readOneProvider")
    void readOne(String args) {
        //TODO: 테스트 작성 필요
        assertThat(args).isEqualTo("");
    }

    static Stream<Arguments> readOneProvider() {
        return Stream.of(
                Arguments.of("")
        );
    }
}
