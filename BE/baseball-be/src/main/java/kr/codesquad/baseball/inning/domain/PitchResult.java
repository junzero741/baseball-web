package kr.codesquad.baseball.inning.domain;

public enum PitchResult {
    S("Strike"), B("Ball"), H("Hit");

    private String description;

    PitchResult(String description) {
        this.description = description;
    }

    public static boolean isStrike(PitchResult pitchResult) {
        return pitchResult == S;
    }

    public String description() {
        return description;
    }

    @Override
    public String toString() {
        return "PitchResult{" +
                "description='" + description + '\'' +
                '}';
    }
}
