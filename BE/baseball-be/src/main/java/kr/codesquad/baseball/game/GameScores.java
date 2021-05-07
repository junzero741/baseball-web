package kr.codesquad.baseball.game;

import java.util.List;

public class GameScores {
    private List<Integer> homeTeamScores;
    private List<Integer> awayTeamScores;

    public GameScores(List<Integer> homeTeamScores, List<Integer> awayTeamScores) {
        this.homeTeamScores = homeTeamScores;
        this.awayTeamScores = awayTeamScores;
    }

    public List<Integer> getHomeTeamScores() {
        return homeTeamScores;
    }

    public List<Integer> getAwayTeamScores() {
        return awayTeamScores;
    }
}
