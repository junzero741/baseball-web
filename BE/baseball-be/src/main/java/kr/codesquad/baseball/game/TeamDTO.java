package kr.codesquad.baseball.game;

import java.util.List;

public class TeamDTO {
    private long id;
    private String name;
    private int score;
    private List<String> players;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<String> getPlayers() {
        return players;
    }
}
