package kr.codesquad.baseball.inning.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class InningRepositoryTest {

    @Autowired
    InningRepository inningRepository;

    @Test
    void save() {
        inningRepository.save(new GameInning(1, 1L, 1L, 1L));
        GameInning secondInning = inningRepository.save(new GameInning(2, 1L, 1L, 1L));

        inningRepository.save(secondInning.updateScore(1));
    }

    @Test
    void savePlateAppearance() {
        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L, 1L));
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
    }

    @Test
    void saveNewPlateAppearance() {
        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L, 1L));
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        inningRepository.save(firstInning.outCurrentHitter());
        inningRepository.save(firstInning.addNewPlateAppearanceBy(2L));
    }

    @Test
    void savePitch() {
        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L, 1L));
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        inningRepository.save(firstInning.pitch(1L, PitchResult.STRIKE));
        inningRepository.save(firstInning.pitch(1L, PitchResult.STRIKE));
    }

    @Test
    void findScoreBy() {
        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L, 1L));
        assertThat(inningRepository.findScoreBy(1, 1L, 1L)).isEqualTo(0);

        inningRepository.save(firstInning.updateScore(2));
        assertThat(inningRepository.findScoreBy(1, 1L, 1L)).isEqualTo(2);
    }

    @Test
    void findAllScoresBy() {
        inningRepository.save(new GameInning(1, 1L, 1L, 1L).updateScore(1));
        inningRepository.save(new GameInning(2, 1L, 1L, 1L).updateScore(3));
        inningRepository.save(new GameInning(3, 1L, 1L, 1L).updateScore(2));

        assertThat(inningRepository.findAllScoresBy(1L, 1L))
                .isEqualTo(Arrays.asList(1, 3, 2));
    }
}
