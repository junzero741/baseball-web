package kr.codesquad.baseball.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @GetMapping("/games")
    public String readAll() {
        return "games";
    }
}
