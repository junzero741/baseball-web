package kr.codesquad.baseball.game.service;

import kr.codesquad.baseball.game.controller.GameDTO;
import kr.codesquad.baseball.game.domain.Game;
import kr.codesquad.baseball.game.domain.GameRepository;
import kr.codesquad.baseball.game.domain.GameStatus;
import kr.codesquad.baseball.team.TeamDTO;
import kr.codesquad.baseball.team.domain.Team;
import kr.codesquad.baseball.team.domain.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    TeamRepository teamRepository;

    GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService(gameRepository, teamRepository);
    }

    @ParameterizedTest
    @MethodSource("readAllProvider")
    void readAll(List<Game> gamesMockup, Team homeTeamMockup, Team awayTeamMockup, List<GameDTO> expected) {
        when(gameRepository.findAll())
                .thenReturn(gamesMockup);

        when(teamRepository.findTeamById(1L))
                .thenReturn(homeTeamMockup);

        when(teamRepository.findTeamById(2L))
                .thenReturn(awayTeamMockup);


        assertThat(gameService.readAll())
                .isEqualTo(expected);
    }

    static Stream<Arguments> readAllProvider() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                Game.create(1L, GameStatus.PLAYING, 1L, 2L)
                        ),
                        Team.create(1L, "Captain"),
                        Team.create(2L, "Marvel"),
                        Arrays.asList(
                                GameDTO.builder()
                                        .id(1L)
                                        .homeTeam(new TeamDTO(1L, "Captain"))
                                        .awayTeam(new TeamDTO(2L, "Marvel"))
                                        .build()
                        )
                )
        );
    }
}
