package kr.codesquad.baseball.game.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    @Test
    void save() {
        GameWithoutInnings gameWithoutInnings = new GameWithoutInnings(GameStatus.PLAYING, new TeamParticipateGame(1L, 2L));
        gameRepository.save(gameWithoutInnings);
    }
}
