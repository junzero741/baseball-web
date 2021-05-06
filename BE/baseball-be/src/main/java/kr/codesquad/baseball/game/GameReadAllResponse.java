package kr.codesquad.baseball.game;

import io.swagger.v3.oas.annotations.media.Schema;

public class GameReadAllResponse {
    private long id;
    private Team homeTeam;
    private Team awayTeam;

    public GameReadAllResponse(long id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public long getId() {
        return id;
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
