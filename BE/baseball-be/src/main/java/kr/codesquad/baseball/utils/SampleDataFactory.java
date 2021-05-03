package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.game.GameReadAllResponse;

import java.util.Arrays;
import java.util.List;

public class SampleDataFactory {
    private SampleDataFactory() {
    }

    public static List<GameReadAllResponse> gameReadAllResponses() {
        return Arrays.asList(
                new GameReadAllResponse(new GameReadAllResponse.Team(1L, "Captain"), new GameReadAllResponse.Team(2L, "Marvel"))
        );
    }
}
