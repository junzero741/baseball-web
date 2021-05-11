package kr.codesquad.baseball.player.domain;

import kr.codesquad.baseball.utils.SampleDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;

    @Test
    void findAll() {
        List<Player> players = new ArrayList<>();

        players.addAll(SampleDataFactory.players());
        players.addAll(SampleDataFactory.players());

        List<Player> result = playerRepository.findAll();

        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getName())
                    .isEqualTo(players.get(i).getName());
        }
    }

    @Test
    void findOne() {
        assertThat(playerRepository.findPlayerById(1L).getName()).isEqualTo("김광진");
    }

    @Test
    void save() {
        Player player = new Player("test");

        long id = playerRepository.save(player).getId();

        Player result = playerRepository.findPlayerById(id);

        assertThat(result.getName()).isEqualTo(player.getName());
    }
}
