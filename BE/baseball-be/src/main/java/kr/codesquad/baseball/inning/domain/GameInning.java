package kr.codesquad.baseball.inning.domain;

import kr.codesquad.baseball.inning.PlateAppearance;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameInning {
    @Id
    private Long id;
    private int inning;
    private long gameId;
    private long teamId;
    private int score;

    @MappedCollection(idColumn = "GAME_INNING_ID", keyColumn = "PLATE_APPEARANCE_NUMBER")
    private List<PlateAppearance> plateAppearances = new ArrayList<>();

    public GameInning(int inning, long gameId, long teamId) {
        this.inning = inning;
        this.gameId = gameId;
        this.teamId = teamId;
    }

    public GameInning addNewPlateAppearanceBy(long hitterId) {
        plateAppearances.add(new PlateAppearance(hitterId, plateAppearances.size() + 1));
        return this;
    }

    public GameInning updateScore(int score) {
        this.score = score;
        return this;
    }

    public GameInning outCurrentHitter() {
        currentPlateAppearance().out();
        return this;
    }

    public PlateAppearance currentPlateAppearance() {
        return plateAppearances.get(plateAppearances.size() - 1);
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

    public List<PlateAppearance> getPlateAppearances() {
        return plateAppearances;
    }

    @Override
    public String toString() {
        return "GameInning{" +
                "id=" + id +
                ", inning=" + inning +
                ", gameId=" + gameId +
                ", teamId=" + teamId +
                ", score=" + score +
                ", plateAppearances=" + plateAppearances +
                '}';
    }
}
