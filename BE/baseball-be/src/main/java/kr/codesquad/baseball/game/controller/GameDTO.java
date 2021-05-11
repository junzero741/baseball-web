package kr.codesquad.baseball.game.controller;

import kr.codesquad.baseball.team.TeamDTO;

public class GameDTO {
    private long id;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;

    public GameDTO(long id, TeamDTO homeTeam, TeamDTO awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public long getId() {
        return id;
    }

    public TeamDTO getHomeTeam() {
        return homeTeam;
    }

    public TeamDTO getAwayTeam() {
        return awayTeam;
    }
}
