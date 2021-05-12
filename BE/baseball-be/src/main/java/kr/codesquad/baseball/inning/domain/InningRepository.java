package kr.codesquad.baseball.inning.domain;

import org.springframework.data.repository.CrudRepository;

public interface InningRepository extends CrudRepository<GameInning, Long> {

    GameInning findTopByGameIdAndTeamIdOrderByInningDesc(long gameId, long teamId);
}
