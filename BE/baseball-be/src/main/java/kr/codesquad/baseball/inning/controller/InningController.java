package kr.codesquad.baseball.inning.controller;

import kr.codesquad.baseball.inning.InningService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class InningController {

    private InningService inningService;

    public InningController(InningService inningService) {
        this.inningService = inningService;
    }

    @GetMapping("/games/{id}")
    public InningDTO readBy(@PathVariable long id, @RequestParam long teamId) {
        return inningService.readOne(id, teamId);
    }
}
