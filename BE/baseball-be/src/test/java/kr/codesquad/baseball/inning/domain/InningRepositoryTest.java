package kr.codesquad.baseball.inning.domain;

import kr.codesquad.baseball.inning.PlateAppearance;
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

        System.out.println(inningRepository.findAll());

        inningRepository.save(secondInning.updateScore(1));
        System.out.println(inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(1L, 1L));
    }

    @Test
    void savePlateAppearance() {

        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L));
        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        System.out.println(inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(1L, 1L));
    }

    @Test
    void saveNewPlateAppearance() {

        GameInning firstInning = inningRepository.save(new GameInning(1, 1L, 1L));

        firstInning.addNewPlateAppearanceBy(1L);

        inningRepository.save(firstInning);
        inningRepository.save(firstInning.outCurrentHitter());
        inningRepository.save(firstInning.addNewPlateAppearanceBy(2L));

        System.out.println(inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(1L, 1L));
    }
}
