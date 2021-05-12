package kr.codesquad.baseball.inning.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InningRepository extends CrudRepository<GameInning, Long> {

    GameInning findTopByGameIdAndTeamIdOrderByInningDesc(long gameId, long teamId);

    @Modifying
    @Query("INSERT INTO " +
            "BASEBALL.GAME_INNING(INNING, GAME_ID, TEAM_ID, SCORE) " +
            "VALUES(:inning, :gameId, :teamId, :score) ")
    int save(@Param("inning") long inning, @Param("gameId") long gameId, @Param("teamId") long teamId, @Param("score") long score);

    @Modifying
    @Query("UPDATE BASEBALL.GAME_INNING " +
            "SET SCORE = :score " +
            "WHERE INNING = :inning AND GAME_ID = :gameId AND TEAM_ID = :teamId")
    int updateScore(@Param("inning") long inning, @Param("gameId") long gameId, @Param("teamId") long teamId, @Param("score") long score);
}
