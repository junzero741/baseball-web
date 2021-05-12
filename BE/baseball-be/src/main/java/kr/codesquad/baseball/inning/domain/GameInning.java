package kr.codesquad.baseball.inning.domain;

import org.springframework.data.annotation.Id;

public class GameInning {
    @Id
    private long inning;
    private long gameId;
    private long teamId;
    private long score;

    public GameInning(long inning, long gameId, long teamId, long score) {
        this.inning = inning;
        this.gameId = gameId;
        this.teamId = teamId;
        this.score = score;
    }

    public long getInning() {
        return inning;
    }

    public long getGameId() {
        return gameId;
    }

    public long getTeamId() {
        return teamId;
    }

    public long getScore() {
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
