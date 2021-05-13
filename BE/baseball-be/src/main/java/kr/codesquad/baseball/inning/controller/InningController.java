package kr.codesquad.baseball.inning.controller;

import kr.codesquad.baseball.inning.InningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class InningController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private InningService inningService;

    public InningController(InningService inningService) {
        this.inningService = inningService;
    }

    @GetMapping("/games/{id}")
    public InningDTO readBy(@PathVariable long id, @RequestParam long teamId) {
        return inningService.readOne(id, teamId);
    }

    //TODO: api 규격 스트릭트하게 검사하도록 수정
    @PostMapping("/games/{id}/pitch")
    @ResponseStatus(HttpStatus.CREATED)
    public InningDTO pitch(@PathVariable long id, @RequestBody PitchRequest pitchRequest) {
        try {
            return inningService.pitch(id, pitchRequest.getTeamId(), pitchRequest.getPitchResult());
        } catch (Exception e) {
            logger.error("id : {}, pitchRequest : {}, e {}", id, pitchRequest, e, e);
        }
        return null;
    }
}
