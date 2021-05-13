package kr.codesquad.baseball.inning.controller;

import kr.codesquad.baseball.inning.domain.PitchResult;

public class PitchRequest {
    private long teamId;
    private PitchResult pitchResult;

    public PitchRequest() {
        this.teamId = teamId;
    }

    public PitchRequest(long teamId, PitchResult pitchResult) {
        this.teamId = teamId;
        this.pitchResult = pitchResult;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public void setPitchResult(PitchResult pitchResult) {
        this.pitchResult = pitchResult;
    }

    public long getTeamId() {
        return teamId;
    }

    public PitchResult getPitchResult() {
        return pitchResult;
    }
}
