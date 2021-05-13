package kr.codesquad.baseball.inning;

import kr.codesquad.baseball.game.controller.GameDTO;
import kr.codesquad.baseball.game.service.GameService;
import kr.codesquad.baseball.inning.controller.HitterRecord;
import kr.codesquad.baseball.inning.controller.InningDTO;
import kr.codesquad.baseball.inning.controller.InningType;
import kr.codesquad.baseball.inning.domain.BaseState;
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

import java.util.Collections;
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
        int currentHitterOrder = attackingTeam.findPlayerBy(hitter.getId()).getHitterOrder();
        int hitCount = inningRepository.hitCountOf(gameId, hitter.getId());

        List<HitterRecord> hitterRecords = gameInning.getPlateAppearances().stream()
                .map(plateAppearance -> new HitterRecord(
                        plateAppearance.getHitterId(),
                        attackingTeam.findPlayerBy(plateAppearance.getHitterId()).getHitterOrder(),
                        playerRepository.findPlayerById(plateAppearance.getHitterId()).getName(),
                        plateAppearance.pitchResults().stream().map(PitchResult::name).collect(Collectors.toList()),
                        plateAppearance.isOut()
                )).collect(Collectors.toList());

        Collections.reverse(hitterRecords);

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

    public InningDTO pitch(long gameId, long teamId, PitchResult pitchResult) {
        GameInning gameInning = inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(gameId, teamId);
        gameInning.pitch(pitchResult);
        inningRepository.save(gameInning);

        // Game에 들어가게 하는게 맞는 구조 같음
        /*
         * 전체 조회용과 하나 조회용의 베이스 하나 두고, 그거 상속받도록 하는 방법 고려해볼 수 있음
         */
        if (gameInning.isCurrentHitterOut()) {
            GameDTO gameDTO = gameService.readOne(gameId);
            InningType inningType = gameInning.inningTypeBy(gameDTO.homeTeamId());

            if (gameInning.getPlateAppearances().stream().filter(plateAppearance -> plateAppearance.isOut()).count() == 3) {
                long nextTeamId = gameDTO.homeTeamId() == teamId ? gameDTO.awayTeamId() : gameDTO.homeTeamId();

                GameInning nextInning = new GameInning(
                        inningType == InningType.BOTTOM ? gameInning.getInning() + 1 : gameInning.getInning(),
                        gameId,
                        nextTeamId,
                        nextTeamId == gameDTO.homeTeamId() ? 6L : 10L
                );

                GameInning lastInning = inningRepository.findTopByGameIdAndTeamIdOrderByInningDesc(gameId, nextTeamId);

                if (lastInning == null) {
                    //TODO: 누가 투수인지 확인 필요
                    //TODO: save가 여기서 이뤄지면 안 됨
                    inningRepository.save(new GameInning(1, gameId, nextTeamId, 10L).addNewPlateAppearanceBy(1L));
                    return readOne(gameId, nextTeamId);
                }

                Team nextAttackingTeam = teamRepository.findTeamById(teamId);
                int currentHitterOrder = nextAttackingTeam.findPlayerBy(lastInning.currentHitterId()).getHitterOrder();
                int nextHitterOrder = currentHitterOrder % 9 + 1;

                long nextHitterId = nextAttackingTeam.getPlayers().stream()
                        .filter(playerOnTeam -> playerOnTeam.getHitterOrder() == nextHitterOrder)
                        .findAny()
                        .orElseThrow(() -> new IllegalStateException("player 순서 조회 중 오류 발생 : " + nextAttackingTeam))
                        .getPlayerId();

                nextInning.addNewPlateAppearanceBy(nextHitterId);

                inningRepository.save(nextInning);

                return readOne(gameId, nextTeamId);
            }

            long attackTeamId = inningType == InningType.BOTTOM ? gameDTO.homeTeamId() : gameDTO.awayTeamId();

            Team attackingTeam = teamRepository.findTeamById(attackTeamId);
            int currentHitterOrder = attackingTeam.findPlayerBy(gameInning.currentHitterId()).getHitterOrder();

            int nextHitterOrder = currentHitterOrder % 9 + 1;

            long nextHitterId = attackingTeam.getPlayers().stream()
                    .filter(playerOnTeam -> playerOnTeam.getHitterOrder() == nextHitterOrder)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("player 순서 조회 중 오류 발생 : " + attackingTeam))
                    .getPlayerId();

            gameInning.addNewPlateAppearanceBy(nextHitterId);
            inningRepository.save(gameInning);

            return readOne(gameId, teamId);
        }

        if (gameInning.currentPlateAppearance().isHit()) {
            GameDTO gameDTO = gameService.readOne(gameId);
            InningType inningType = gameInning.inningTypeBy(gameDTO.homeTeamId());

            long attackTeamId = inningType == InningType.BOTTOM ? gameDTO.homeTeamId() : gameDTO.awayTeamId();

            Team attackingTeam = teamRepository.findTeamById(attackTeamId);
            int currentHitterOrder = attackingTeam.findPlayerBy(gameInning.currentHitterId()).getHitterOrder();

            int nextHitterOrder = currentHitterOrder % 9 + 1;

            long nextHitterId = attackingTeam.getPlayers().stream()
                    .filter(playerOnTeam -> playerOnTeam.getHitterOrder() == nextHitterOrder)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("player 순서 조회 중 오류 발생 : " + attackingTeam))
                    .getPlayerId();

            BaseState baseState = gameInning.getBaseState();
            if (baseState.isThirdBase()) {
                gameInning.updateScore(gameInning.getScore() + 1);
            } else if (baseState.isSecondBase()) {
                baseState.setThirdBase(true);
            } else if (baseState.isFirstBase()) {
                baseState.setSecondBase(true);
            } else {
                baseState.setFirstBase(true);
            }

            gameInning.addNewPlateAppearanceBy(nextHitterId);

            inningRepository.save(gameInning);
        }

        return readOne(gameId, teamId);
    }

    public int totalScoreOf(long gameId, long teamId) {
        return inningRepository.findAllScoresBy(gameId, teamId).stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
