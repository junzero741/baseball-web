package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.game.domain.GameWithoutInnings;
import kr.codesquad.baseball.game.domain.GameRepository;
import kr.codesquad.baseball.game.domain.GameStatus;
import kr.codesquad.baseball.game.domain.TeamParticipateGame;
import kr.codesquad.baseball.inning.domain.GameInning;
import kr.codesquad.baseball.inning.domain.InningRepository;
import kr.codesquad.baseball.player.domain.Player;
import kr.codesquad.baseball.player.domain.PlayerRepository;
import kr.codesquad.baseball.team.domain.Team;
import kr.codesquad.baseball.team.domain.TeamRepository;
import kr.codesquad.baseball.user.User;
import kr.codesquad.baseball.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Configuration
public class SampleDataRunner {
    @Bean
    @Order(10)
    public ApplicationRunner saveUser(UserRepository userRepository) {
        return args -> userRepository.saveAll(
                Arrays.asList(
                        new User("test"),
                        new User("test2")
                )
        );
    }

    @Bean
    @Order(10)
    public ApplicationRunner savePlayer(PlayerRepository playerRepository) {
        return args -> {
            List<Player> players = new ArrayList<>();
            players.addAll(SampleDataFactory.players());
            players.addAll(SampleDataFactory.players());
            playerRepository.saveAll(players);
        };
    }

    @Bean
    @Order(20)
    public ApplicationRunner saveTeam(TeamRepository teamRepository, PlayerRepository playerRepository) {
        return args -> {
            Team captain = new Team("Captain");
            captain.addPlayers(playerRepository.findAllById(LongStream.range(1, 10).boxed().collect(Collectors.toList())));

            Team marvel = new Team("Marvel");
            marvel.addPlayers(playerRepository.findAllById(LongStream.range(10, 19).boxed().collect(Collectors.toList())));

            teamRepository.saveAll(new ArrayList<>(
                    Arrays.asList(
                            captain,
                            marvel
                    )
            ));
        };
    }

    @Bean
    @Order(30)
    public ApplicationRunner saveGame(GameRepository gameRepository, InningRepository inningRepository) {
        return args -> {
            GameWithoutInnings gameWithoutInnings = new GameWithoutInnings(GameStatus.PLAYING, new TeamParticipateGame(1L, 2L));

            gameRepository.save(gameWithoutInnings);

            // 게임 초기화 시 이닝도 함께 초기회돼야 함.
            GameInning gameInning = new GameInning(1, gameWithoutInnings.getId(), gameWithoutInnings.homeTeamId(), 7L);
            gameInning.addNewPlateAppearanceBy(10L);
            inningRepository.save(gameInning);
        };
    }
}
