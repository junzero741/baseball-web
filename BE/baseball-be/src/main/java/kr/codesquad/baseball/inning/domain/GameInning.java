package kr.codesquad.baseball.inning.domain;

import kr.codesquad.baseball.inning.controller.InningType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.ArrayList;
import java.util.List;

public class GameInning {
    @Id
    private Long id;
    private int inning;
    private long gameId;
    private long teamId;
    private int score;
    private long pitcherId;

    @MappedCollection(idColumn = "GAME_INNING_ID", keyColumn = "PLATE_APPEARANCE_NUMBER")
    private List<PlateAppearance> plateAppearances = new ArrayList<>();

    @Column("GAME_INNING_ID")
    private BaseState baseState = new BaseState();

    public GameInning(int inning, long gameId, long teamId, long pitcherId) {
        this.inning = inning;
        this.gameId = gameId;
        this.teamId = teamId;
        this.pitcherId = pitcherId;
    }

    public GameInning pitch(PitchResult pitchResult) {
        currentPlateAppearance().pitch(pitcherId, pitchResult);
        return this;
    }

    public int pitchCount() {
        return pitchCount(pitcherId);
    }

    public int pitchCount(long pitcherId) {
        return Math.toIntExact(plateAppearances.parallelStream()
                .flatMap(plateAppearance -> plateAppearance.getPitches().stream())
                .filter(pitch -> pitch.getPitcherId() == pitcherId)
                .count());
    }

    public GameInning addNewPlateAppearanceBy(long hitterId, int plateAppearanceNumber) {
        plateAppearances.add(new PlateAppearance(hitterId, plateAppearanceNumber + 1));
        return this;
    }

    public GameInning addNewPlateAppearanceBy(long hitterId) {
        return addNewPlateAppearanceBy(hitterId, 0);
    }

    public GameInning updateScore(int score) {
        this.score = score;
        return this;
    }

    public long currentHitterId() {
        return currentPlateAppearance().getHitterId();
    }

    public boolean isCurrentHitterOut() {
        return currentPlateAppearance().isOut();
    }

    public int currentHitterPlateAppearanceNumber() {
        return currentPlateAppearance().getPlateAppearanceNumber();
    }

    public PlateAppearance currentPlateAppearance() {
        return plateAppearances.get(plateAppearances.size() - 1);
    }

    public InningType inningTypeBy(long homeTeamId) {
        if (teamId == homeTeamId) {
            return InningType.TOP;
        }

        return InningType.BOTTOM;
    }

    public Long getId() {
        return id;
    }

    public int getInning() {
        return inning;
    }

    public long getGameId() {
        return gameId;
    }

    public long getTeamId() {
        return teamId;
    }

    public int getScore() {
        return score;
    }

    public long getPitcherId() {
        return pitcherId;
    }

    public List<PlateAppearance> getPlateAppearances() {
        return plateAppearances;
    }

    public BaseState getBaseState() {
        return baseState;
    }

    @Override
    public String toString() {
        return "GameInning{" +
                "id=" + id +
                ", inning=" + inning +
                ", gameId=" + gameId +
                ", teamId=" + teamId +
                ", score=" + score +
                ", pitcherId=" + pitcherId +
                ", plateAppearances=" + plateAppearances +
                ", baseState=" + baseState +
                '}';
    }
}
