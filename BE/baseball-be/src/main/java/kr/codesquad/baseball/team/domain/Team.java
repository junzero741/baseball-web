package kr.codesquad.baseball.team.domain;

import kr.codesquad.baseball.player.domain.Player;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.ArrayList;
import java.util.List;

public class Team {
    @Id
    private Long id;
    private String name;

    @MappedCollection(idColumn = "TEAM_ID", keyColumn = "HITTER_ORDER")
    private List<PlayerOnTeam> players = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addPlayers(List<Player> players) {
        this.players.addAll(PlayerOnTeam.teamHasPlayers(players));
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
