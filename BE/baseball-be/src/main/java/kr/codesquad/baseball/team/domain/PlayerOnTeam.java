package kr.codesquad.baseball.team.domain;

import kr.codesquad.baseball.player.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerOnTeam {
    private long playerId;
    private int hitterOrder;

    public PlayerOnTeam(long playerId, int hitterOrder) {
        this.playerId = playerId;
        this.hitterOrder = hitterOrder;
    }

    public static List<PlayerOnTeam> teamHasPlayers(List<Player> players) {
        List<PlayerOnTeam> playerOnTeams = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            playerOnTeams.add(from(players.get(i), i + 1));
        }

        return playerOnTeams;
    }

    public static PlayerOnTeam from(Player player, int hitterOrder) {
        return new PlayerOnTeam(player.getId(), hitterOrder);
    }

    public long getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "TeamHasPlayer{" +
                "playerId=" + playerId +
                ", hitterOrder=" + hitterOrder +
                '}';
    }
}
