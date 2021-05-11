package kr.codesquad.baseball.game;

import kr.codesquad.baseball.team.TeamDTO;

public class LineUpDTO {
    private TeamDTO.WithPlayerStatus homeTeam;
    private TeamDTO.WithPlayerStatus awayTeam;

    public LineUpDTO(TeamDTO.WithPlayerStatus homeTeam, TeamDTO.WithPlayerStatus awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public TeamDTO.WithPlayerStatus getHomeTeam() {
        return homeTeam;
    }

    public TeamDTO.WithPlayerStatus getAwayTeam() {
        return awayTeam;
    }
}
