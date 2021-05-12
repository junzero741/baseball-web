package kr.codesquad.baseball.game.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findAll();

    Game findGameById(long id);
}
