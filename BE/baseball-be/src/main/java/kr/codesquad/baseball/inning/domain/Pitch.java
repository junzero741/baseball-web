package kr.codesquad.baseball.inning.domain;

import org.springframework.data.annotation.Id;

public class Pitch {
    @Id
    private Long id;
    private long pitcherId;
    private int pitchNumber;
    private PitchResult result;

    public Pitch(long pitcherId, int pitchNumber, PitchResult result) {
        this.pitcherId = pitcherId;
        this.pitchNumber = pitchNumber;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public long getPitcherId() {
        return pitcherId;
    }

    public int getPitchNumber() {
        return pitchNumber;
    }

    public PitchResult getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Pitch{" +
                "id=" + id +
                ", pitcherId=" + pitcherId +
                ", pitchNumber=" + pitchNumber +
                ", result=" + result +
                '}';
    }
}
