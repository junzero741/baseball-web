package kr.codesquad.baseball.game.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Game {
    @Id
    private Long id;
    private GameStatus gameStatus;

    @Column("GAME_ID")
    private TeamParticipateGame teamParticipateGame;

    public Game(GameStatus gameStatus, TeamParticipateGame teamParticipateGame) {
        this.gameStatus = gameStatus;
        this.teamParticipateGame = teamParticipateGame;
    }

    public static Game create(long id, GameStatus gameStatus, long homeTeamId, long awayTeamId) {
        Game game = new Game(gameStatus, new TeamParticipateGame(homeTeamId, awayTeamId));
        game.id = id;
        return game;
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
