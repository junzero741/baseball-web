package kr.codesquad.baseball.team;

import kr.codesquad.baseball.game.controller.PlayerStatus;
import kr.codesquad.baseball.team.domain.Team;

import java.util.List;
import java.util.Objects;

public class TeamDTO {
    private long id;
    private String name;

    public TeamDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TeamDTO from(Team team) {
        return new TeamDTO(team.getId(), team.getName());
    }

    public WithScore withScore(int score) {
        return new WithScore(id, name, score);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDTO teamDTO = (TeamDTO) o;
        return id == teamDTO.id && Objects.equals(name, teamDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
