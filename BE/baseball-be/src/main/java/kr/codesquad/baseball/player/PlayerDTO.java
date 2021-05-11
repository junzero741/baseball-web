package kr.codesquad.baseball.player;

public class PlayerDTO {
    private long id;
    private String name;

    public PlayerDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Pitcher extends PlayerDTO {
        private int pitchCount;

        public Pitcher(long id, String name, int pitchCount) {
            super(id, name);
            this.pitchCount = pitchCount;
        }

        public int getPitchCount() {
            return pitchCount;
        }
    }

    public static class Hitter extends PlayerDTO {
        private int battingOrder;
        private int plateAppearanceCount;
        private int hitCount;

        public Hitter(long id, String name, int battingOrder, int plateAppearanceCount, int hitCount) {
            super(id, name);
            this.battingOrder = battingOrder;
            this.plateAppearanceCount = plateAppearanceCount;
            this.hitCount = hitCount;
        }

        public int getBattingOrder() {
            return battingOrder;
        }

        public int getPlateAppearanceCount() {
            return plateAppearanceCount;
        }

        public int getHitCount() {
            return hitCount;
        }
    }
}
