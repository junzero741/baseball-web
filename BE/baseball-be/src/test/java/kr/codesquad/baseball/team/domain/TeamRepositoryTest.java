package kr.codesquad.baseball.team.domain;

import kr.codesquad.baseball.player.domain.Player;
import kr.codesquad.baseball.player.domain.PlayerRepository;
import kr.codesquad.baseball.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SpringBootTest
@Transactional
class TeamRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        Team team = new Team("test");
        List<Player> players = playerRepository.findAllById(LongStream.range(1, 10).boxed().collect(Collectors.toList()));
        team.addPlayers(players);

        teamRepository.save(team);
    }

    @Test
    void addUser() {
        Team team = teamRepository.findTeamById(1L).addUser(userRepository.findUserById(1L));
        teamRepository.save(team);
    }

    @Test
    void removeUser() {
        Team team = teamRepository.findTeamById(1L).addUser(userRepository.findUserById(1L));
        teamRepository.save(team);

        logger.debug("remove start");

        teamRepository.save(team.removeUser());
    }
}
