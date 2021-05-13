package kr.codesquad.baseball.game.service;

import kr.codesquad.baseball.game.controller.GameDTO;
import kr.codesquad.baseball.game.controller.GameScoresDTO;
import kr.codesquad.baseball.game.controller.LineUpDTO;
import kr.codesquad.baseball.game.controller.PlayerStatus;
import kr.codesquad.baseball.game.domain.GameRepository;
import kr.codesquad.baseball.game.domain.GameWithInnings;
import kr.codesquad.baseball.game.domain.GameWithoutInnings;
import kr.codesquad.baseball.inning.domain.InningRepository;
import kr.codesquad.baseball.inning.domain.PlateAppearance;
import kr.codesquad.baseball.player.domain.PlayerRepository;
import kr.codesquad.baseball.team.TeamDTO;
import kr.codesquad.baseball.team.domain.Team;
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

    @Autowired
    private PlayerRepository playerRepository;

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
        return gameToGameDTO(gameRepository.findGameWithoutInningsById(id));
    }

    private GameDTO gameToGameDTO(GameWithoutInnings gameWithoutInnings) {
        return GameDTO.builder()
                .id(gameWithoutInnings)
                .homeTeam(teamRepository.findTeamById(gameWithoutInnings.homeTeamId()))
                .awayTeam(teamRepository.findTeamById(gameWithoutInnings.awayTeamId()))
                .build();
    }

    public GameScoresDTO scores(long gameId) {
        GameDTO gameDTO = readOne(gameId);

        TeamDTO homeTeam = gameDTO.getHomeTeam();
        TeamDTO awayTeam = gameDTO.getAwayTeam();

        List<Integer> homeTeamScores = inningRepository.findAllScoresBy(gameId, awayTeam.getId());
        List<Integer> awayTeamScores = inningRepository.findAllScoresBy(gameId, homeTeam.getId());

        GameScoresDTO gameScoresDTO = new GameScoresDTO(homeTeam.withScores(homeTeamScores), awayTeam.withScores(awayTeamScores));

        return gameScoresDTO;
    }

    public LineUpDTO lineUp(long gameId) {
        GameWithInnings gameWithInnings = gameRepository.findGameWithInningsById(gameId);

        Team homeTeam = teamRepository.findTeamById(gameWithInnings.getTeamParticipateGame().getHomeTeamId());
        Team awayTeam = teamRepository.findTeamById(gameWithInnings.getTeamParticipateGame().getAwayTeamId());

        List<PlayerStatus> homeTeamPlayerStatuses = homeTeam.getPlayers().stream()
                .map(playerOnTeam -> new PlayerStatus(
                        playerOnTeam.getPlayerId(),
                        playerRepository.findPlayerById(playerOnTeam.getPlayerId()).getName(),
                        plateAppearanceNumber(gameId, playerOnTeam.getPlayerId()),
                        hitCountOf(gameId, playerOnTeam.getPlayerId())
                ))
                .collect(Collectors.toList());

        List<PlayerStatus> awayTeamPlayerStatuses = awayTeam.getPlayers().stream()
                .map(playerOnTeam -> new PlayerStatus(
                        playerOnTeam.getPlayerId(),
                        playerRepository.findPlayerById(playerOnTeam.getPlayerId()).getName(),
                        plateAppearanceNumber(gameId, playerOnTeam.getPlayerId()),
                        hitCountOf(gameId, playerOnTeam.getPlayerId())
                ))
                .collect(Collectors.toList());

        TeamDTO.WithPlayerStatus homeTeamWithPlayerStatus = TeamDTO.from(homeTeam).withPlayerStatus(homeTeamPlayerStatuses);
        TeamDTO.WithPlayerStatus awayTeamWithPlayerStatus = TeamDTO.from(awayTeam).withPlayerStatus(awayTeamPlayerStatuses);

        return new LineUpDTO(homeTeamWithPlayerStatus, awayTeamWithPlayerStatus);
    }

    public int pitchCountOf(long gameId, long pitcherId) {
        GameWithInnings gameWithInnings = gameRepository.findGameWithInningsById(gameId);

        return gameWithInnings.getGameInnings().stream()
                .map(gameInning -> gameInning.pitchCount(pitcherId))
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public int hitCountOf(long gameId, long hitterId) {
        GameWithInnings gameWithInnings = gameRepository.findGameWithInningsById(gameId);

        return Math.toIntExact(gameWithInnings.getGameInnings().stream()
                .flatMap(gameInning -> gameInning.getPlateAppearances().stream())
                .filter(plateAppearance -> plateAppearance.getHitterId() == hitterId)
                .filter(PlateAppearance::isHit)
                .count());
    }

    public int plateAppearanceNumber(long gameId, long hitterId) {
        GameWithInnings gameWithInnings = gameRepository.findGameWithInningsById(gameId);

        return Math.toIntExact(gameWithInnings.getGameInnings().stream()
                .flatMap(gameInning -> gameInning.getPlateAppearances().stream())
                .filter(plateAppearance -> plateAppearance.getHitterId() == hitterId)
                .count());
    }
}
