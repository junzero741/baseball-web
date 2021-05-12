package kr.codesquad.baseball.game.domain;

public class TeamParticipateGame {
    private long homeTeamId;
    private long awayTeamId;

    public TeamParticipateGame(long homeTeamId, long awayTeamId) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public long getHomeTeamId() {
        return homeTeamId;
    }

    public long getAwayTeamId() {
        return awayTeamId;
    }

    @Override
    public String toString() {
        return "TeamParticipateGame{" +
                "homeTeamId=" + homeTeamId +
                ", awayTeamId=" + awayTeamId +
                '}';
    }
}
