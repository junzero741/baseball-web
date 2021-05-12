package kr.codesquad.baseball.inning.domain;

public enum PitchResult {
    STRIKE("S"), BALL("B"), HIT("H");

    private String symbol;

    PitchResult(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}
