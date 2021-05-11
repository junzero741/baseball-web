package kr.codesquad.baseball.game.controller;

import kr.codesquad.baseball.team.TeamDTO;

public class GameScoresDTO {
    private TeamDTO.WithScores homeTeam;
    private TeamDTO.WithScores awayTeam;

    public GameScoresDTO(TeamDTO.WithScores homeTeam, TeamDTO.WithScores awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public TeamDTO.WithScores getHomeTeam() {
        return homeTeam;
    }

    public TeamDTO.WithScores getAwayTeam() {
        return awayTeam;
    }
}
