package kr.codesquad.baseball.game.controller;

import kr.codesquad.baseball.game.domain.Game;
import kr.codesquad.baseball.team.TeamDTO;
import kr.codesquad.baseball.team.domain.Team;

import java.util.Objects;

public class GameDTO {
    private long id;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;

    public GameDTO(long id, TeamDTO homeTeam, TeamDTO awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public static GameDTO of(Game game, Team homeTeam, Team awayTeam) {
        return new GameDTO(game.getId(), TeamDTO.from(homeTeam), TeamDTO.from(awayTeam));
    }

    public static GameDTOBuilder builder() {
        return new GameDTOBuilder();
    }

    public long homeTeamId() {
        return homeTeam.getId();
    }

    public long awayTeamId() {
        return awayTeam.getId();
    }

    public TeamDTO.WithScore homeTeamWithScore(int score) {
        return homeTeam.withScore(score);
    }

    public TeamDTO.WithScore awayTeamWithScore(int score) {
        return awayTeam.withScore(score);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDTO gameDTO = (GameDTO) o;
        return id == gameDTO.id && Objects.equals(homeTeam, gameDTO.homeTeam) && Objects.equals(awayTeam, gameDTO.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homeTeam, awayTeam);
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }

    public static final class GameDTOBuilder {
        private long id;
        private TeamDTO homeTeam;
        private TeamDTO awayTeam;

        private GameDTOBuilder() {
        }

        public GameDTOBuilder id(long id) {
            this.id = id;
            return this;
        }

        public GameDTOBuilder id(Game game) {
            this.id = game.getId();
            return this;
        }

        public GameDTOBuilder homeTeam(TeamDTO homeTeam) {
            this.homeTeam = homeTeam;
            return this;
        }

        public GameDTOBuilder homeTeam(Team homeTeam) {
            this.homeTeam = TeamDTO.from(homeTeam);
            return this;
        }

        public GameDTOBuilder awayTeam(TeamDTO awayTeam) {
            this.awayTeam = awayTeam;
            return this;
        }

        public GameDTOBuilder awayTeam(Team awayTeam) {
            this.awayTeam = TeamDTO.from(awayTeam);
            return this;
        }

        public GameDTO build() {
            return new GameDTO(id, homeTeam, awayTeam);
        }
    }
}
