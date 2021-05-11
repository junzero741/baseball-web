package kr.codesquad.baseball.team.repository;

import kr.codesquad.baseball.player.repository.Player;
import kr.codesquad.baseball.player.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SpringBootTest
@Transactional
class TeamRepositoryTest {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    void findAll() {
        System.out.println(teamRepository.findAll());
    }

    @Test
    void save() {
        Team team = new Team("test");

        List<Player> players = playerRepository.findAllById(LongStream.range(1, 10).boxed().collect(Collectors.toList()));

        team.addPlayers(players);

        Team result = teamRepository.save(team);

        System.out.println(result);
    }
}
