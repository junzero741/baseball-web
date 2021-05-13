package kr.codesquad.baseball.inning.domain;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InningRepository extends CrudRepository<GameInning, Long> {

    boolean existsByGameIdAndTeamIdAndInning(long gameId, long teamId, int inning);

    GameInning findTopByGameIdOrderByIdDesc(long id);

    GameInning findTopByGameIdAndTeamIdOrderByInningDesc(long gameId, long teamId);

    @Query("SELECT SCORE FROM GAME_INNING WHERE INNING=:inning AND GAME_ID=:gameId AND TEAM_ID=:teamId")
    int findScoreBy(@Param("inning") int inning, @Param("gameId") long gameId, @Param("teamId") long teamId);

    @Query("SELECT SCORE FROM GAME_INNING WHERE GAME_ID=:gameId AND TEAM_ID=:teamId")
    List<Integer> findAllScoresBy(@Param("gameId") long gameId, @Param("teamId") long teamId);

    @Query("SELECT COUNT(*) " +
            "FROM GAME_INNING GI " +
            "JOIN PLATE_APPEARANCE PA ON PA.GAME_INNING_ID = GI.ID " +
            "JOIN PITCH P ON P.PLATE_APPEARANCE_ID = PA.ID  " +
            "WHERE GI.GAME_ID = :gameId " +
            "AND PA.HITTER_ID = :hitterId " +
            "AND P.RESULT = 'H'")
    int hitCountOf(@Param("gameId") long gameId, @Param("hitterId") long hitterId);
}
