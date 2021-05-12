package kr.codesquad.baseball.inning.domain;

import org.springframework.data.annotation.Id;

public class GameInning {
    @Id
    private Long id;
    private int inning;
    private long gameId;
    private long teamId;
    private int score;

    public GameInning(int inning, long gameId, long teamId) {
        this.inning = inning;
        this.gameId = gameId;
        this.teamId = teamId;
    }

    public GameInning updateScore(int score) {
        this.score = score;
        return this;
    }

    public Long getId() {
        return id;
    }

    public int getInning() {
        return inning;
    }

    public long getGameId() {
        return gameId;
    }

    public long getTeamId() {
        return teamId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "GameInning{" +
                "inning=" + inning +
                ", gameId=" + gameId +
                ", teamId=" + teamId +
                ", score=" + score +
                '}';
    }
}
