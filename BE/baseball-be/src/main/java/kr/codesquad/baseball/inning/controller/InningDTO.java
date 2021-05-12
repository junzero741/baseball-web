package kr.codesquad.baseball.inning.controller;

import kr.codesquad.baseball.inning.domain.BaseState;
import kr.codesquad.baseball.player.PlayerDTO;
import kr.codesquad.baseball.team.TeamDTO;

import java.util.List;

public class InningDTO {
    private int inning;
    private InningType inningType;
    private TeamDTO.WithScore homeTeam;
    private TeamDTO.WithScore awayTeam;
    private BaseState baseState;
    private PlayerDTO.Pitcher currentPitcher;
    private PlayerDTO.Hitter currentHitter;
    private List<HitterRecord> hitterRecords;

    public InningDTO(int inning, InningType inningType, TeamDTO.WithScore homeTeam, TeamDTO.WithScore awayTeam, BaseState baseState, PlayerDTO.Pitcher currentPitcher, PlayerDTO.Hitter currentHitter, List<HitterRecord> hitterRecords) {
        this.inning = inning;
        this.inningType = inningType;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.baseState = baseState;
        this.currentPitcher = currentPitcher;
        this.currentHitter = currentHitter;
        this.hitterRecords = hitterRecords;
    }

    public static InningDTOBuilder builder() {
        return new InningDTOBuilder();
    }

    public int getInning() {
        return inning;
    }

    public InningType getInningType() {
        return inningType;
    }

    public TeamDTO.WithScore getHomeTeam() {
        return homeTeam;
    }

    public TeamDTO.WithScore getAwayTeam() {
        return awayTeam;
    }

    public BaseState getBaseState() {
        return baseState;
    }

    public PlayerDTO.Pitcher getCurrentPitcher() {
        return currentPitcher;
    }

    public PlayerDTO.Hitter getCurrentHitter() {
        return currentHitter;
    }

    public List<HitterRecord> getHitterRecords() {
        return hitterRecords;
    }

    public static final class InningDTOBuilder {
        private int inning;
        private InningType inningType;
        private TeamDTO.WithScore homeTeam;
        private TeamDTO.WithScore awayTeam;
        private BaseState baseState;
        private PlayerDTO.Pitcher currentPitcher;
        private PlayerDTO.Hitter currentHitter;
        private List<HitterRecord> hitterRecords;

        private InningDTOBuilder() {
        }

        public InningDTOBuilder inning(int inning) {
            this.inning = inning;
            return this;
        }

        public InningDTOBuilder inningType(InningType inningType) {
            this.inningType = inningType;
            return this;
        }

        public InningDTOBuilder homeTeam(TeamDTO.WithScore homeTeam) {
            this.homeTeam = homeTeam;
            return this;
        }

        public InningDTOBuilder awayTeam(TeamDTO.WithScore awayTeam) {
            this.awayTeam = awayTeam;
            return this;
        }

        public InningDTOBuilder baseState(BaseState baseState) {
            this.baseState = baseState;
            return this;
        }

        public InningDTOBuilder currentPitcher(PlayerDTO.Pitcher currentPitcher) {
            this.currentPitcher = currentPitcher;
            return this;
        }

        public InningDTOBuilder currentHitter(PlayerDTO.Hitter currentHitter) {
            this.currentHitter = currentHitter;
            return this;
        }

        public InningDTOBuilder hitterRecords(List<HitterRecord> hitterRecords) {
            this.hitterRecords = hitterRecords;
            return this;
        }

        public InningDTO build() {
            return new InningDTO(inning, inningType, homeTeam, awayTeam, baseState, currentPitcher, currentHitter, hitterRecords);
        }
    }
}
