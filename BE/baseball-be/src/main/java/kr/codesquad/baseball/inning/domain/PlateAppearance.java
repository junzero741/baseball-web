package kr.codesquad.baseball.inning.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlateAppearance {
    @Id
    private Long id;
    private long hitterId;
    private int plateAppearanceNumber;
    private boolean isOut;

    @MappedCollection(idColumn = "PLATE_APPEARANCE_ID", keyColumn = "PITCH_NUMBER")
    private List<Pitch> pitches = new ArrayList<>();

    public PlateAppearance(long hitterId, int plateAppearanceNumber) {
        this.hitterId = hitterId;
        this.plateAppearanceNumber = plateAppearanceNumber;
    }

    public PlateAppearance pitch(long pitcherId, PitchResult pitchResult) {
        pitches.add(new Pitch(pitcherId, pitchCount() + 1, pitchResult));

        if (pitchResults().stream().filter(PitchResult::isStrike).count() == 3) {
            isOut = true;
        }

        return this;
    }

    public int pitchCount() {
        if (pitches.size() == 0) {
            return 0;
        }

        return latestPitch().getPitchNumber();
    }

    public Pitch latestPitch() {
        return pitches.get(pitches.size() - 1);
    }

    public List<PitchResult> pitchResults() {
        return pitches.stream().map(Pitch::getResult).collect(Collectors.toList());
    }

    public void out() {
        isOut = true;
    }

    public Long getId() {
        return id;
    }

    public long getHitterId() {
        return hitterId;
    }

    public int getPlateAppearanceNumber() {
        return plateAppearanceNumber;
    }

    public boolean isOut() {
        return isOut;
    }

    public List<Pitch> getPitches() {
        return pitches;
    }

    @Override
    public String toString() {
        return "PlateAppearance{" +
                "id=" + id +
                ", hitterId=" + hitterId +
                ", plateAppearanceNumber=" + plateAppearanceNumber +
                ", isOut=" + isOut +
                ", pitches=" + pitches +
                '}';
    }
}
