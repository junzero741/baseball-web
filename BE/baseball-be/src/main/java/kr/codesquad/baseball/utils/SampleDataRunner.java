package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.player.repository.Player;
import kr.codesquad.baseball.player.repository.PlayerRepository;
import kr.codesquad.baseball.team.repository.Team;
import kr.codesquad.baseball.team.repository.TeamRepository;
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
}
