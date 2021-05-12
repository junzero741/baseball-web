package kr.codesquad.baseball.inning.domain;

import org.springframework.data.annotation.Id;

public class PlateAppearance {
    @Id
    private Long id;
    private long hitterId;
    private int plateAppearanceNumber;
    private boolean isOut;

    public PlateAppearance(long hitterId, int plateAppearanceNumber) {
        this.hitterId = hitterId;
        this.plateAppearanceNumber = plateAppearanceNumber;
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
                '}';
    }
}
