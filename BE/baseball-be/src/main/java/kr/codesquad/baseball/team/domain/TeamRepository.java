package kr.codesquad.baseball.team.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findAll();

    Team findTeamById(long id);
}
