package kr.codesquad.baseball.inning.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayDeque;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class InningRepositoryTest {

    @Autowired
    InningRepository inningRepository;

    @Test
    void save() {
        inningRepository.save(new GameInning(1, 1L, 2L, 1L));
        GameInning secondInning = inningRepository.save(new GameInning(2, 1L, 1L, 1L));

        inningRepository.save(secondInning.updateScore(1));
    }

    @Test
    void findTopByIdOrderByInningDesc() {
        new ArrayDeque<>(inningRepository.findAllById(1L));
    }

    @Test
    void savePlateAppearance() {
        GameInning firstInning = inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(1L, 1L);
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
    }

    @Test
    void saveNewPlateAppearance() {
        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 2L, 1L));
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        firstInning.currentPlateAppearance().out();
        inningRepository.save(firstInning);
        inningRepository.save(firstInning.addNewPlateAppearanceBy(2L));
    }

    @Test
    void savePitch() {
        GameInning firstInning = inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(1L, 1L);
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        inningRepository.save(firstInning.pitch(PitchResult.S));
        inningRepository.save(firstInning.pitch(PitchResult.S));
    }

    @Test
    void findScoreBy() {
        assertThat(inningRepository.findScoreBy(1, 1L, 1L)).isEqualTo(0);
        GameInning gameInning = inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(1L, 1L);
        inningRepository.save(gameInning.updateScore(2));
        assertThat(inningRepository.findScoreBy(1, 1L, 1L)).isEqualTo(2);
    }

    @Test
    void findAllScoresBy() {
        inningRepository.save(inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(1L, 1L).updateScore(1));
        inningRepository.save(new GameInning(2, 1L, 1L, 1L).updateScore(3));
        inningRepository.save(new GameInning(3, 1L, 1L, 1L).updateScore(2));

        assertThat(inningRepository.findAllScoresBy(1L, 1L))
                .isEqualTo(Arrays.asList(1, 3, 2));
    }

    @Test
    void hitCountOf() {
        GameInning firstInning = inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(1L, 1L);
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        inningRepository.save(firstInning.pitch(PitchResult.S));
        inningRepository.save(firstInning.pitch(PitchResult.S));
        inningRepository.save(firstInning.pitch(PitchResult.H));

        firstInning.addNewPlateAppearanceBy(1L, 1);
        inningRepository.save(firstInning.pitch(PitchResult.H));

        assertThat(inningRepository.hitCountOf(1L, 1L)).isEqualTo(2);
    }
}
