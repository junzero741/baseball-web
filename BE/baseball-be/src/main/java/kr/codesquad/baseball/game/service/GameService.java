package kr.codesquad.baseball.game.service;

import kr.codesquad.baseball.game.controller.GameDTO;
import kr.codesquad.baseball.game.controller.GameScoresDTO;
import kr.codesquad.baseball.game.domain.Game;
import kr.codesquad.baseball.game.domain.GameRepository;
import kr.codesquad.baseball.inning.domain.InningRepository;
import kr.codesquad.baseball.team.TeamDTO;
import kr.codesquad.baseball.team.domain.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private GameRepository gameRepository;
    private TeamRepository teamRepository;

    @Autowired
    private InningRepository inningRepository;

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

    public GameScoresDTO scores(long gameId) {
        GameDTO gameDTO = readOne(gameId);

        TeamDTO homeTeam = gameDTO.getHomeTeam();
        TeamDTO awayTeam = gameDTO.getAwayTeam();

        List<Integer> homeTeamScores = inningRepository.findAllScoresBy(gameId, homeTeam.getId());
        List<Integer> awayTeamScores = inningRepository.findAllScoresBy(gameId, awayTeam.getId());

        GameScoresDTO gameScoresDTO = new GameScoresDTO(homeTeam.withScores(homeTeamScores), awayTeam.withScores(awayTeamScores));

        return gameScoresDTO;
    }
}
