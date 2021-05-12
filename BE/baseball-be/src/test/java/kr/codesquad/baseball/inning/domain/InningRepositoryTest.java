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
        inningRepository.save(new GameInning(1, 1L, 1L));
        GameInning secondInning = inningRepository.save(new GameInning(2, 1L, 1L));

        inningRepository.save(secondInning.updateScore(1));
    }

    @Test
    void savePlateAppearance() {
        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L));
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
    }

    @Test
    void saveNewPlateAppearance() {
        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L));
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        inningRepository.save(firstInning.outCurrentHitter());
        inningRepository.save(firstInning.addNewPlateAppearanceBy(2L));
    }

    @Test
    void savePitch() {
        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L));
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        inningRepository.save(firstInning.pitch(1L, PitchResult.STRIKE));
        inningRepository.save(firstInning.pitch(1L, PitchResult.STRIKE));
    }
}
