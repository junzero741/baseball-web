package kr.codesquad.baseball.game;

import io.swagger.v3.oas.annotations.media.Schema;

public class GameReadAllResponse {
    private long id;
    // 야구는 홈팀이 후공이라고 한다.
    private Team homeTeam;
    private Team awayTeam;

    public GameReadAllResponse(long id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    @Schema(name = "Team-GameReadAllResponse")
    public static class Team {
        private long id;
        private String name;

        public Team(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
