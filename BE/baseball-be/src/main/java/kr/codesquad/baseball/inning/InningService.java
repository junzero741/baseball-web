package kr.codesquad.baseball.inning;

import kr.codesquad.baseball.game.controller.GameDTO;
import kr.codesquad.baseball.game.service.GameService;
import kr.codesquad.baseball.inning.controller.HitterRecord;
import kr.codesquad.baseball.inning.controller.InningDTO;
import kr.codesquad.baseball.inning.controller.InningType;
import kr.codesquad.baseball.inning.domain.GameInning;
import kr.codesquad.baseball.inning.domain.InningRepository;
import kr.codesquad.baseball.inning.domain.PitchResult;
import kr.codesquad.baseball.player.PlayerDTO;
import kr.codesquad.baseball.player.domain.Player;
import kr.codesquad.baseball.player.domain.PlayerRepository;
import kr.codesquad.baseball.team.TeamDTO;
import kr.codesquad.baseball.team.domain.Team;
import kr.codesquad.baseball.team.domain.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InningService {

    private InningRepository inningRepository;
    private TeamRepository teamRepository;
    private GameService gameService;
    private PlayerRepository playerRepository;

    public InningService(InningRepository inningRepository, TeamRepository teamRepository, GameService gameService, PlayerRepository playerRepository) {
        this.inningRepository = inningRepository;
        this.teamRepository = teamRepository;
        this.gameService = gameService;
        this.playerRepository = playerRepository;
    }

    public InningDTO readOne(long gameId, long teamId) {
        GameInning gameInning = inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(gameId, teamId);
        if (gameInning == null) {
            //TODO: 누가 투수인지 확인 필요
            //TODO: save가 여기서 이뤄지면 안 됨
            gameInning = inningRepository.save(new GameInning(1, gameId, teamId, 10L));
        }

        GameDTO gameDTO = gameService.readOne(gameId);
        InningType inningType = gameInning.inningTypeBy(gameDTO.homeTeamId());

        int homeTeamTotalScore = totalScoreOf(gameId, gameDTO.homeTeamId());
        int awayTeamTotalScore = totalScoreOf(gameId, gameDTO.awayTeamId());

        TeamDTO.WithScore homeTeam = gameDTO.homeTeamWithScore(homeTeamTotalScore);
        TeamDTO.WithScore awayTeam = gameDTO.awayTeamWithScore(awayTeamTotalScore);

        Player pitcher = playerRepository.findPlayerById(gameInning.getPitcherId());
        Player hitter = playerRepository.findPlayerById(gameInning.currentHitterId());

        long attackTeamId = inningType == InningType.BOTTOM ? homeTeam.getId() : awayTeam.getId();

        Team attackingTeam = teamRepository.findTeamById(attackTeamId);
        int currentHitterOrder = attackingTeam.getPlayers().get(attackingTeam.findPlayerBy(hitter.getId()).getHitterOrder()).getHitterOrder();
        int hitCount = inningRepository.hitCountOf(gameId, hitter.getId());

        List<HitterRecord> hitterRecords = gameInning.getPlateAppearances().stream()
                .map(plateAppearance -> new HitterRecord(
                        plateAppearance.getHitterId(),
                        attackingTeam.findPlayerBy(plateAppearance.getHitterId()).getHitterOrder(),
                        playerRepository.findPlayerById(plateAppearance.getHitterId()).getName(),
                        plateAppearance.pitchResults().stream().map(PitchResult::symbol).collect(Collectors.toList()),
                        plateAppearance.isOut()
                )).collect(Collectors.toList());

        InningDTO inningDTO = InningDTO.builder()
                .inning(gameInning.getInning())
                .inningType(inningType)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .baseState(gameInning.getBaseState())
                .currentPitcher(PlayerDTO.Pitcher.of(pitcher, gameInning.pitchCount()))
                .currentHitter(PlayerDTO.Hitter.of(hitter, currentHitterOrder, gameInning.currentHitterPlateAppearanceNumber(), hitCount))
                .hitterRecords(hitterRecords)
                .build();

        return inningDTO;
    }

    public int totalScoreOf(long gameId, long teamId) {
        return inningRepository.findAllScoresBy(gameId, teamId).stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
