package com.smunity.api.domain.petition.controller;

import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.dto.RespondDto;
import com.smunity.api.domain.petition.service.CommentService;
import com.smunity.api.domain.petition.service.PetitionService;
import com.smunity.api.domain.petition.service.RespondService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/petitions/{petitionId}/responds")
public class RespondController {
    private final RespondService respondService;

    public RespondController(RespondService respondService) {
        this.respondService = respondService;
    }

    @PostMapping()
    public ResponseEntity<RespondDto> createRespond(@PathVariable Long petitionId, @RequestBody RespondDto respondDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        RespondDto answer = respondService.createRespond(petitionId, respondDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping()
    ResponseEntity<RespondDto> getRespond(@PathVariable Long petitionId) {
        RespondDto respondDto = respondService.getRespond(petitionId);
        return ResponseEntity.status(HttpStatus.OK).body(respondDto);
    }

    @PutMapping()
    ResponseEntity<RespondDto> changeRespond(@PathVariable Long petitionId, @RequestBody RespondDto respondDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        RespondDto answer = respondService.changeRespond(petitionId, respondDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @DeleteMapping()
    ResponseEntity<?> deleteRespond(@PathVariable Long petitionId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        respondService.deleteRespond(petitionId, token);
        return ResponseEntity.noContent().build();
    }
}
