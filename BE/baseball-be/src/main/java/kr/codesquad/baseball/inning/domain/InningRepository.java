package kr.codesquad.baseball.inning.domain;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InningRepository extends CrudRepository<GameInning, Long> {

    GameInning findTopByGameIdAndTeamIdOrderByInningDesc(long gameId, long teamId);

    @Query("SELECT SCORE FROM GAME_INNING WHERE INNING=:inning AND GAME_ID=:gameId AND TEAM_ID=:teamId")
    int findScoreBy(@Param("inning") int inning, @Param("gameId") long gameId, @Param("teamId") long teamId);

    @Query("SELECT SCORE FROM GAME_INNING WHERE GAME_ID=:gameId AND TEAM_ID=:teamId")
    List<Integer> findAllScoresBy(@Param("gameId") long gameId, @Param("teamId") long teamId);
}
