package kr.codesquad.baseball.game;

import java.util.List;

public class LineUp {
    private Team homeTeam;
    private Team awayTeam;

    public LineUp(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public static class Team {
        private long id;
        private String name;
        private List<PlayerStatus> playerStatuses;

        public Team(long id, String name, List<PlayerStatus> playerStatuses) {
            this.id = id;
            this.name = name;
            this.playerStatuses = playerStatuses;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<PlayerStatus> getPlayerStatuses() {
            return playerStatuses;
        }
    }
}
