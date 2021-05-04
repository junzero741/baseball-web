package kr.codesquad.baseball.game;

import kr.codesquad.baseball.utils.SampleDataFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GameController {
    @GetMapping("/games")
    public List<GameReadAllResponse> readAll() {
        return SampleDataFactory.gameReadAllResponses();
    }
}
