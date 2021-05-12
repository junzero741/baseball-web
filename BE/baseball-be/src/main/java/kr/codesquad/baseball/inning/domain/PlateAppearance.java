package kr.codesquad.baseball.inning.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.ArrayList;
import java.util.List;

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
        pitches.add(new Pitch(pitcherId, pitches.size() + 1, pitchResult));
        return this;
    }

    public void out() {
        isOut = true;
    }

    public Long getId() {
        return id;
    }

    public int getPlateAppearanceNumber() {
        return plateAppearanceNumber;
    }

    public boolean isOut() {
        return isOut;
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
