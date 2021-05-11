package kr.codesquad.baseball.inning;

public class BaseState {
    private boolean firstBase;
    private boolean secondBase;
    private boolean thirdBase;

    public BaseState(boolean firstBase, boolean secondBase, boolean thirdBase) {
        this.firstBase = firstBase;
        this.secondBase = secondBase;
        this.thirdBase = thirdBase;
    }

    public boolean isFirstBase() {
        return firstBase;
    }

    public boolean isSecondBase() {
        return secondBase;
    }

    public boolean isThirdBase() {
        return thirdBase;
    }
}
