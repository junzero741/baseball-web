package kr.codesquad.baseball.game;

import java.util.List;

/**
 * 상단의 스코어를 메인메뉴 재활용 느낌으로 사용하고,
 * 아래에 딸려나오는 기록들은 다른 요청으로 처리할 것인지
 * 한 방 구조로 나가는게 나을지
 */
public class GameReadOneResponse {
    private long id;
    private Team homeTeam;
    private Team awayTeam;
    private List<Inning> innings;

    public static class Team {
        private long id;
        private String name;
        private int score;
        private List<String> players;
    }
}
