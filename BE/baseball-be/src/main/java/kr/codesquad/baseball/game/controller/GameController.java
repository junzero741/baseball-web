package kr.codesquad.baseball.game.controller;

import kr.codesquad.baseball.utils.SampleDataFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GameController {
    @GetMapping("/games")
    public List<GameDTO> readAll() {
        return SampleDataFactory.gameReadAllResponses();
    }

    @GetMapping("/games/{id}/lineup")
    public LineUpDTO lineUp(@PathVariable long id) {
        return SampleDataFactory.lineUp();
    }

    @GetMapping("/games/{id}/scores")
    public GameScoresDTO gameScores(@PathVariable long id) {
        return SampleDataFactory.gameScores();
    }
}
