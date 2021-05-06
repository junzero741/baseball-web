package kr.codesquad.baseball.game;

import java.util.List;

public class Inning {
    private int inning;
    // 아니면 아예 이닝 자체를 나누는 방법도 생각해볼 수 있음
    private String type; // 초(Top or away), 말(bottom or home)
    private List<PlateAppearance> plateAppearances;
    private BaseState baseState;
    private int score;
}
