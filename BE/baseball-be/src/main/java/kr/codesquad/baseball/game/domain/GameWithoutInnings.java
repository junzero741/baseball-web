package kr.codesquad.baseball.game.domain;

import org.springframework.data.relational.core.mapping.Table;

@Table("GAME")
public class GameWithoutInnings extends GameBase {

    public GameWithoutInnings(GameStatus gameStatus, TeamParticipateGame teamParticipateGame) {
        super(gameStatus, teamParticipateGame);
    }

    public static GameWithoutInnings create(long id, GameStatus gameStatus, long homeTeamId, long awayTeamId) {
        GameWithoutInnings gameWithoutInnings = new GameWithoutInnings(gameStatus, new TeamParticipateGame(homeTeamId, awayTeamId));
        gameWithoutInnings.id = id;
        return gameWithoutInnings;
    }

    public long homeTeamId() {
        return teamParticipateGame.getHomeTeamId();
    }

    public long awayTeamId() {
        return teamParticipateGame.getAwayTeamId();
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
        return "Game{" +
                "id=" + id +
                ", gameStatus=" + gameStatus +
                ", teamParticipateGame=" + teamParticipateGame +
                '}';
    }
}
