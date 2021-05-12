package kr.codesquad.baseball.inning.controller;

import kr.codesquad.baseball.inning.domain.BaseState;
import kr.codesquad.baseball.player.PlayerDTO;
import kr.codesquad.baseball.team.TeamDTO;

import java.util.List;

public class InningDTO {
    private int inning;
    private String inningType;
    private TeamDTO.WithScore homeTeam;
    private TeamDTO.WithScore awayTeam;
    private BaseState baseState;
    private PlayerDTO.Pitcher currentPitcher;
    private PlayerDTO.Hitter currentHitter;
    private List<HitterRecord> hitterRecords;

    public InningDTO(int inning, String inningType, TeamDTO.WithScore homeTeam, TeamDTO.WithScore awayTeam, BaseState baseState, PlayerDTO.Pitcher currentPitcher, PlayerDTO.Hitter currentHitter, List<HitterRecord> hitterRecords) {
        this.inning = inning;
        this.inningType = inningType;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.baseState = baseState;
        this.currentPitcher = currentPitcher;
        this.currentHitter = currentHitter;
        this.hitterRecords = hitterRecords;
    }

    public int getInning() {
        return inning;
    }

    public String getInningType() {
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
}
