package com.smunity.api.domain.petition.controller;

import com.smunity.api.domain.petition.dto.RespondDto;
import com.smunity.api.domain.petition.service.RespondService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/petitions/{petitionId}/responds")
public class RespondController {
    private final RespondService respondService;

    @PostMapping()
    public ResponseEntity<RespondDto> createRespond(@PathVariable Long petitionId, @RequestBody RespondDto respondDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        RespondDto answer = respondService.createRespond(petitionId, respondDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping()
    ResponseEntity<RespondDto> getRespondByPetitionId(@PathVariable Long petitionId) {
        RespondDto respondDto = respondService.getRespondByPetitionId(petitionId);
        return ResponseEntity.status(HttpStatus.OK).body(respondDto);
    }

    @PutMapping()
    ResponseEntity<RespondDto> updateRespond(@PathVariable Long petitionId, @RequestBody RespondDto respondDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        RespondDto answer = respondService.updateRespond(petitionId, respondDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @DeleteMapping()
    ResponseEntity<?> deleteRespond(@PathVariable Long petitionId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        respondService.deleteRespond(petitionId, token);
        return ResponseEntity.noContent().build();
    }
}
