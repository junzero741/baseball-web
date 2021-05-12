package kr.codesquad.baseball.inning.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class InningRepositoryTest {

    @Autowired
    InningRepository inningRepository;

    @Test
    void save() {

        inningRepository.save(1, 1L, 1L, 0);
        inningRepository.save(2, 1L, 1L, 0);

        System.out.println(inningRepository.findAll());

        inningRepository.updateScore(2, 1L, 1L, 1);
        System.out.println(inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc( 1L, 1L));
    }
}
