package kr.codesquad.baseball.game;

import java.util.List;

/**
 * 받아놓은 정보로 렌더링 할 수 있다면 만들지 않는게 나을 수 있음.
 */
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
