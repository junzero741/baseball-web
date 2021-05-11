package kr.codesquad.baseball.team;

import kr.codesquad.baseball.game.controller.PlayerStatus;

import java.util.List;

public class TeamDTO {
    private long id;
    private String name;

    public TeamDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class WithScore extends TeamDTO {
        private int score;

        public WithScore(long id, String name, int score) {
            super(id, name);
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }

    public static class WithScores extends TeamDTO {
        private List<Integer> scores;

        public WithScores(long id, String name, List<Integer> scores) {
            super(id, name);
            this.scores = scores;
        }

        public List<Integer> getScores() {
            return scores;
        }
    }

    public static class WithPlayerStatus extends TeamDTO {
        private List<PlayerStatus> playerStatuses;

        public WithPlayerStatus(long id, String name, List<PlayerStatus> playerStatuses) {
            super(id, name);
            this.playerStatuses = playerStatuses;
        }

        public List<PlayerStatus> getPlayerStatuses() {
            return playerStatuses;
        }
    }
}
