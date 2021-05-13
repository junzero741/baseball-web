package kr.codesquad.baseball.game.domain;

import kr.codesquad.baseball.inning.domain.GameInning;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table("GAME")
public class GameWithInnings extends GameBase {

    @MappedCollection(idColumn = "GAME_ID", keyColumn = "INNING")
    List<GameInning> gameInnings = new ArrayList<>();

    public GameWithInnings(GameStatus gameStatus, TeamParticipateGame teamParticipateGame) {
        super(gameStatus, teamParticipateGame);
    }

    public List<GameInning> getGameInnings() {
        return gameInnings;
    }

    @Override
    public String toString() {
        return "GameWithInnings{" +
                "id=" + id +
                ", gameStatus=" + gameStatus +
                ", teamParticipateGame=" + teamParticipateGame +
                ", gameInnings=" + gameInnings +
                '}';
    }
}
