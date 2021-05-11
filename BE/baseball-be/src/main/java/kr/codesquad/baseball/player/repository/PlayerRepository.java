package kr.codesquad.baseball.player.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAll();

    Player findPlayerById(long id);
}
