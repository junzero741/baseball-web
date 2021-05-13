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

    public void setFirstBase(boolean firstBase) {
        this.firstBase = firstBase;
    }

    public boolean isSecondBase() {
        return secondBase;
    }

    public void setSecondBase(boolean secondBase) {
        this.secondBase = secondBase;
    }

    public boolean isThirdBase() {
        return thirdBase;
    }

    public void setThirdBase(boolean thirdBase) {
        this.thirdBase = thirdBase;
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
