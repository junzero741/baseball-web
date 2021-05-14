package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.game.domain.GameRepository;
import kr.codesquad.baseball.game.domain.GameStatus;
import kr.codesquad.baseball.game.domain.GameWithoutInnings;
import kr.codesquad.baseball.game.domain.TeamParticipateGame;
import kr.codesquad.baseball.inning.domain.GameInning;
import kr.codesquad.baseball.inning.domain.InningRepository;
import kr.codesquad.baseball.player.domain.Player;
import kr.codesquad.baseball.player.domain.PlayerRepository;
import kr.codesquad.baseball.team.domain.Team;
import kr.codesquad.baseball.team.domain.TeamRepository;
import kr.codesquad.baseball.user.User;
import kr.codesquad.baseball.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SampleDataRunner {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    InningRepository inningRepository;

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
    public ApplicationRunner saveTeam() {
        return args -> {
            saveTeam(SampleDataFactory.backendPlayers(), "Backend");
            saveTeam(SampleDataFactory.frontend(), "Frontend");

            saveTeam(SampleDataFactory.players(), "Codesquad");
            saveTeam(SampleDataFactory.players(), "WoowahanTechCourse");
            saveTeam(SampleDataFactory.players(), "Naver");
            saveTeam(SampleDataFactory.players(), "Kakao");
            saveTeam(SampleDataFactory.players(), "Line");
            saveTeam(SampleDataFactory.players(), "Coopang");
            saveTeam(SampleDataFactory.players(), "NHN");
            saveTeam(SampleDataFactory.players(), "SKT");
            saveTeam(SampleDataFactory.players(), "KT");
            saveTeam(SampleDataFactory.players(), "LGT");

        };
    }

    private void saveTeam(List<Player> players, String teamName) {
        playerRepository.saveAll(players);
        Team team = new Team(teamName);
        team.addPlayers(players);
        teamRepository.save(team);

    }

    @Bean
    @Order(30)
    public ApplicationRunner saveGame() {
        return args -> {
            saveGame(1L, 2L);
            saveGame(3L, 4L);
            saveGame(5L, 6L);
            saveGame(7L, 8L);
            saveGame(9L, 10L);
            saveGame(11L, 12L);

        };
    }

    private void saveGame(long homeTeamId, long awayTeamId) {
        GameWithoutInnings gameWithoutInnings = new GameWithoutInnings(GameStatus.PLAYING, new TeamParticipateGame(homeTeamId, awayTeamId));
        Team homeTeam = teamRepository.findTeamById(homeTeamId);
        Team awayTeam = teamRepository.findTeamById(awayTeamId);

        gameRepository.save(gameWithoutInnings);

        GameInning gameInning = new GameInning(1, gameWithoutInnings.getId(), gameWithoutInnings.homeTeamId(), homeTeam.getPlayers().get(0).getPlayerId());
        gameInning.addNewPlateAppearanceBy(awayTeam.getPlayers().get(0).getPlayerId());
        inningRepository.save(gameInning);
    }
}
