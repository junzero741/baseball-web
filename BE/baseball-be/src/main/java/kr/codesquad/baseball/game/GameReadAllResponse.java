package kr.codesquad.baseball.game;

public class GameReadAllResponse {
    private Team homeTeam;
    private Team awayTeam;

    public GameReadAllResponse(Team homeTeam, Team awayTeam) {
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
