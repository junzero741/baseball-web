package kr.codesquad.baseball.game.service;

import kr.codesquad.baseball.game.controller.GameDTO;
import kr.codesquad.baseball.game.domain.Game;
import kr.codesquad.baseball.game.domain.GameRepository;
import kr.codesquad.baseball.team.domain.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private GameRepository gameRepository;
    private TeamRepository teamRepository;

    public GameService(GameRepository gameRepository, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    public List<GameDTO> readAll() {
        return gameRepository.findAll().stream()
                .map(this::gameToGameDTO)
                .collect(Collectors.toList());
    }

    public GameDTO readOne(long id) {
        return gameToGameDTO(gameRepository.findGameById(id));
    }

    private GameDTO gameToGameDTO(Game game) {
        return GameDTO.builder()
                .id(game)
                .homeTeam(teamRepository.findTeamById(game.homeTeamId()))
                .awayTeam(teamRepository.findTeamById(game.awayTeamId()))
                .build();
    }

}
