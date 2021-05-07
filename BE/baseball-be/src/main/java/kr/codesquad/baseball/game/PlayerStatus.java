package kr.codesquad.baseball.game;

public class PlayerStatus {
    private long id;
    private String name;
    private int plateAppearanceCount;
    private int hitCount;

    public PlayerStatus(long id, String name, int plateAppearanceCount, int hitCount) {
        this.id = id;
        this.name = name;
        this.plateAppearanceCount = plateAppearanceCount;
        this.hitCount = hitCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPlateAppearanceCount() {
        return plateAppearanceCount;
    }

    public int getHitCount() {
        return hitCount;
    }
}
