package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.game.GameReadAllResponse;
import kr.codesquad.baseball.game.GameScores;
import kr.codesquad.baseball.game.LineUp;
import kr.codesquad.baseball.game.PlayerStatus;
import kr.codesquad.baseball.inning.BaseState;
import kr.codesquad.baseball.inning.HitterRecord;
import kr.codesquad.baseball.inning.InningDTO;

import java.util.Arrays;
import java.util.List;

public class SampleDataFactory {
    private SampleDataFactory() {
    }

    public static List<GameReadAllResponse> gameReadAllResponses() {
        return Arrays.asList(
                new GameReadAllResponse(1L, new GameReadAllResponse.Team(1L, "Captain"), new GameReadAllResponse.Team(2L, "Marvel")),
                new GameReadAllResponse(2L, new GameReadAllResponse.Team(3L, "Twins"), new GameReadAllResponse.Team(4L, "Tigers")),
                new GameReadAllResponse(3L, new GameReadAllResponse.Team(5L, "Rockets"), new GameReadAllResponse.Team(6L, "Dodgers")),
                new GameReadAllResponse(4L, new GameReadAllResponse.Team(7L, "United"), new GameReadAllResponse.Team(8L, "City")),
                new GameReadAllResponse(5L, new GameReadAllResponse.Team(9L, "Wolves"), new GameReadAllResponse.Team(10L, "Dragons")),
                new GameReadAllResponse(6L, new GameReadAllResponse.Team(11L, "Reds"), new GameReadAllResponse.Team(12L, "Lions"))
        );
    }

    public static InningDTO inningDTO() {
        return new InningDTO(
                2,
                "TOP",
                new InningDTO.Team(1L, "Captain", 1),
                new InningDTO.Team(2L, "Marvel", 5),
                new BaseState(false, true, true),
                new InningDTO.Pitcher(8L, "최동원", 39),
                new InningDTO.Hitter(16L, 7, "류현진", 1, 0),
                Arrays.asList(
                        new HitterRecord(
                                16L,
                                7,
                                "류현진",
                                Arrays.asList("S", "B", "B", "B", "S"),
                                true
                        ), new HitterRecord(
                                15L,
                                6,
                                "이용대",
                                Arrays.asList("S", "B", "B"),
                                false
                        ), new HitterRecord(
                                14L,
                                5,
                                "추신수",
                                Arrays.asList("S", "S", "B", "S"),
                                true
                        )
                )
        );
    }

    public static LineUp lineUp() {
        return new LineUp(
                new LineUp.Team(
                        1L,
                        "Captain",
                        Arrays.asList(
                                new PlayerStatus(
                                        1L,
                                        "김광진",
                                        1,
                                        1
                                ), new PlayerStatus(
                                        2L,
                                        "이동규",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        3L,
                                        "김진수",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        4L,
                                        "박영권",
                                        1,
                                        1
                                ), new PlayerStatus(
                                        5L,
                                        "추신수",
                                        1,
                                        1
                                ), new PlayerStatus(
                                        6L,
                                        "이용대",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        7L,
                                        "류현진",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        8L,
                                        "최동수",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        9L,
                                        "한양범",
                                        1,
                                        1
                                )
                        )
                ),
                new LineUp.Team(
                        2L,
                        "Marvel",
                        Arrays.asList(
                                new PlayerStatus(
                                        10L,
                                        "김광진",
                                        1,
                                        1
                                ), new PlayerStatus(
                                        11L,
                                        "이동규",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        12L,
                                        "김진수",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        13L,
                                        "박영권",
                                        1,
                                        1
                                ), new PlayerStatus(
                                        14L,
                                        "추신수",
                                        1,
                                        1
                                ), new PlayerStatus(
                                        15L,
                                        "이용대",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        16L,
                                        "류현진",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        17L,
                                        "최동원",
                                        1,
                                        0
                                ), new PlayerStatus(
                                        18L,
                                        "한양범",
                                        1,
                                        1
                                )
                        )
                )
        );
    }

    public static GameScores gameScores() {
        return new GameScores(
                Arrays.asList(1, 2, 2),
                Arrays.asList(1, 0, 0, 0)
        );
    }
}
