package kr.codesquad.baseball.player.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAll();

    List<Player> findAllById(Iterable<Long> ids);

    Player findPlayerById(long id);
}
