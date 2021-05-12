package kr.codesquad.baseball.inning.controller;

import kr.codesquad.baseball.utils.SampleDataFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class InningController {
    @GetMapping("/games/{id}")
    public InningDTO readBy(@PathVariable long id, @RequestParam long teamId) {
        return SampleDataFactory.inningDTO();
    }
}
