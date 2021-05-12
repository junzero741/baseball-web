package kr.codesquad.baseball.team.domain;

import kr.codesquad.baseball.player.domain.Player;
import kr.codesquad.baseball.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

public class Team {
    @Id
    private Long id;
    private String name;

    @MappedCollection(idColumn = "TEAM_ID", keyColumn = "HITTER_ORDER")
    private List<PlayerOnTeam> players = new ArrayList<>();

    @PersistenceConstructor
    public Team(String name) {
        this.name = name;
    }

    private Team(Long id, String name, List<PlayerOnTeam> players) {
        this(name);

        this.id = id;
        this.players = players;
    }

    public void addPlayers(List<Player> players) {
        this.players.addAll(PlayerOnTeam.teamHasPlayers(players));
    }

    public Team addUser(User user) {
        return new ControlledByUser(id, name, players, new UserControlTeam(user.getId()));
    }

    public Team removeUser() {
        return new Team(id, name, players);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PlayerOnTeam> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + players +
                '}';
    }

    @Table("TEAM")
    public static class ControlledByUser extends Team {
        @Column("TEAM_ID")
        private UserControlTeam userControlTeam;

        public ControlledByUser(Long id, String name, List<PlayerOnTeam> players, UserControlTeam userControlTeam) {
            super(id, name, players);
            this.userControlTeam = userControlTeam;
        }

        public UserControlTeam getUserControlTeam() {
            return userControlTeam;
        }

        @Override
        public String toString() {
            return "ControlledByUser{" +
                    "id=" + getId() +
                    ", name='" + getName() + '\'' +
                    ", players=" + getPlayers() +
                    ", userControlTeam=" + userControlTeam +
                    '}';
        }
    }
}
