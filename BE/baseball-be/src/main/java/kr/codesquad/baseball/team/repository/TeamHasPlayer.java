package kr.codesquad.baseball.team.repository;

import kr.codesquad.baseball.player.repository.Player;

import java.util.List;
import java.util.stream.Collectors;

public class TeamHasPlayer {
    private long playerId;

    public TeamHasPlayer(long playerId) {
        this.playerId = playerId;
    }

    public static List<TeamHasPlayer> teamHasPlayers(List<Player> players) {
        return players.stream()
                .map(player -> TeamHasPlayer.from(player))
                .collect(Collectors.toList());
    }

    public static TeamHasPlayer from(Player player) {
        return new TeamHasPlayer(player.getId());
    }

    public long getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "TeamHasPlayer{" +
                "playerId=" + playerId +
                '}';
    }
}
