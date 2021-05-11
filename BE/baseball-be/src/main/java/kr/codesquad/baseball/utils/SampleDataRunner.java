package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.player.repository.Player;
import kr.codesquad.baseball.player.repository.PlayerRepository;
import kr.codesquad.baseball.user.User;
import kr.codesquad.baseball.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SampleDataRunner {
    @Bean
    public ApplicationRunner saveUser(UserRepository userRepository) {
        return args -> userRepository.saveAll(
                Arrays.asList(
                        new User("test"),
                        new User("test2")
                )
        );
    }

    @Bean
    public ApplicationRunner savePlayer(PlayerRepository playerRepository) {
        List<Player> players = new ArrayList<>();
        players.addAll(SampleDataFactory.players());
        players.addAll(SampleDataFactory.players());

        return args -> playerRepository.saveAll(players);
    }
}
