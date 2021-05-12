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
    void findOne() {
        assertThat(playerRepository.findPlayerById(1L).getName()).isEqualTo("김광진");
    }

    @Test
    void save() {
        Player player = new Player("test");
        playerRepository.save(player);
    }
}
