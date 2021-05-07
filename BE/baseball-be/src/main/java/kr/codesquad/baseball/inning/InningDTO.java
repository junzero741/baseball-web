package kr.codesquad.baseball.inning;

import java.util.List;

public class InningDTO {
    private int inning;
    private String inningType;
    private Team homeTeam;
    private Team awayTeam;
    private BaseState baseState;
    private Pitcher currentPitcher;
    private Hitter currentHitter;
    private List<HitterRecord> hitterRecords;

    public InningDTO(int inning, String inningType, Team homeTeam, Team awayTeam, BaseState baseState, Pitcher currentPitcher, Hitter currentHitter, List<HitterRecord> hitterRecords) {
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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public BaseState getBaseState() {
        return baseState;
    }

    public Pitcher getCurrentPitcher() {
        return currentPitcher;
    }

    public Hitter getCurrentHitter() {
        return currentHitter;
    }

    public List<HitterRecord> getHitterRecords() {
        return hitterRecords;
    }

    public static class Team {
        private long id;
        private String name;
        private int score;


        public Team(long id, String name, int score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }

    public static class Pitcher {
        private long id;
        private String name;
        private int pitchCount;

        public Pitcher(long id, String name, int pitchCount) {
            this.id = id;
            this.name = name;
            this.pitchCount = pitchCount;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPitchCount() {
            return pitchCount;
        }
    }

    public static class Hitter {
        private long id;
        private int battingOrder;
        private String name;
        private int plateAppearanceCount;
        private int hitCount;

        public Hitter(long id, int battingOrder, String name, int plateAppearanceCount, int hitCount) {
            this.id = id;
            this.battingOrder = battingOrder;
            this.name = name;
            this.plateAppearanceCount = plateAppearanceCount;
            this.hitCount = hitCount;
        }

        public long getId() {
            return id;
        }

        public int getBattingOrder() {
            return battingOrder;
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
}
