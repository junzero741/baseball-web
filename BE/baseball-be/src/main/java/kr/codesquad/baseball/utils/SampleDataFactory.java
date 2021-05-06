package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.game.GameReadAllResponse;

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
}
