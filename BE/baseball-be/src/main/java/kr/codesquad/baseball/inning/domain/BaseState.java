package kr.codesquad.baseball.inning.domain;

public class BaseState {
    private boolean firstBase;
    private boolean secondBase;
    private boolean thirdBase;

    public BaseState() {
    }

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

    @Override
    public String toString() {
        return "BaseState{" +
                "firstBase=" + firstBase +
                ", secondBase=" + secondBase +
                ", thirdBase=" + thirdBase +
                '}';
    }
}
