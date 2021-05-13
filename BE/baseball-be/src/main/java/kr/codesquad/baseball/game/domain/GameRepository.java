package kr.codesquad.baseball.game.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<GameWithoutInnings, Long> {
    List<GameWithoutInnings> findAll();

    GameWithoutInnings findGameWithoutInningsById(long id);

    GameWithInnings findGameWithInningsById(long id);
}
