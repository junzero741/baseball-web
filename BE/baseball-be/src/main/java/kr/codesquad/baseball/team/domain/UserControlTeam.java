package kr.codesquad.baseball.team.domain;

public class UserControlTeam {
    private long userId;

    public UserControlTeam(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserControlTeam{" +
                "userId=" + userId +
                '}';
    }
}
