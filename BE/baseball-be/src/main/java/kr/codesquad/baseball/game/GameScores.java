package kr.codesquad.baseball.game;

import java.util.List;

public class GameScores {
    private Team homeTeam;
    private Team awayTeam;

    public GameScores(Team homeTeam, Team awayTeam) {
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
        private List<Integer> scores;

        public Team(long id, String name, List<Integer> scores) {
            this.id = id;
            this.name = name;
            this.scores = scores;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<Integer> getScores() {
            return scores;
        }
    }
}
