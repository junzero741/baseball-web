package kr.codesquad.baseball.game.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class GameBase {
    @Id
    protected Long id;
    protected GameStatus gameStatus;

    @Column("GAME_ID")
    protected TeamParticipateGame teamParticipateGame;

    public GameBase(GameStatus gameStatus, TeamParticipateGame teamParticipateGame) {
        this.gameStatus = gameStatus;
        this.teamParticipateGame = teamParticipateGame;
    }

    public Long getId() {
        return id;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public TeamParticipateGame getTeamParticipateGame() {
        return teamParticipateGame;
    }

    @Override
    public String toString() {
        return "GameBase{" +
                "id=" + id +
                ", gameStatus=" + gameStatus +
                ", teamParticipateGame=" + teamParticipateGame +
                '}';
    }
}
