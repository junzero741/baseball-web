package kr.codesquad.baseball.team.domain;

import kr.codesquad.baseball.player.domain.Player;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team {
    @Id
    private Long id;
    private String name;

    @MappedCollection(idColumn = "TEAM_ID")
    private Set<TeamHasPlayer> players = new HashSet<>();

    public Team(String name) {
        this.name = name;
    }

    public void addPlayers(List<Player> players) {
        this.players.addAll(TeamHasPlayer.teamHasPlayers(players));
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + players +
                '}';
    }
}
