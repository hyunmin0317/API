package com.smunity.api.domain.petition.controller;

import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.dto.RespondDto;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.service.CommentService;
import com.smunity.api.domain.petition.service.RespondService;
import com.smunity.api.domain.petition.service.PetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/petitions")
public class PetitionController {
    private final PetitionService petitionService;
    private final RespondService respondService;
    private final CommentService commentService;

    public PetitionController(PetitionService petitionService, RespondService respondService, CommentService commentService) {
        this.petitionService = petitionService;
        this.respondService = respondService;
        this.commentService = commentService;
    }

    @GetMapping()
    ResponseEntity<List<PetitionDto>> findAllPetitions() {
        List<PetitionDto> petitionDtoList = petitionService.findAllPetitions();
        return ResponseEntity.status(HttpStatus.OK).body(petitionDtoList);
    }

    @PostMapping()
    public ResponseEntity<PetitionDto> createPetition(@RequestBody PetitionDto petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        PetitionDto petition = petitionService.createPetition(petitionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<PetitionDto> getPetition(@PathVariable Long id) {
        PetitionDto petitionDto = petitionService.getPetition(id);
        return ResponseEntity.status(HttpStatus.OK).body(petitionDto);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<PetitionDto> changePetition(@PathVariable Long id, @RequestBody PetitionDto petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        PetitionDto petition = petitionService.changePetition(id, petitionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> deletePetition(@PathVariable Long id, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        petitionService.deletePetition(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping(value = "/{id}/responds")
    public ResponseEntity<RespondDto> createRespond(@PathVariable Long id, @RequestBody RespondDto respondDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        RespondDto answer = respondService.createRespond(id, respondDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping(value = "/{id}/responds")
    ResponseEntity<RespondDto> getRespond(@PathVariable Long id) {
        RespondDto respondDto = respondService.getRespond(id);
        return ResponseEntity.status(HttpStatus.OK).body(respondDto);
    }

    @PutMapping(value = "/{id}/responds")
    ResponseEntity<RespondDto> changeRespond(@PathVariable Long id, @RequestBody RespondDto respondDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        RespondDto answer = respondService.changeRespond(id, respondDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @DeleteMapping(value = "/{id}/responds")
    ResponseEntity<String> deleteRespond(@PathVariable Long id, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        respondService.deleteRespond(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping(value = "/{petitionId}/comments")
    public ResponseEntity<List<CommentDto>> findAllComment(@PathVariable Long petitionId) {
        List<CommentDto> commentDtoList = commentService.findAllComments(petitionId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
    }

    @PostMapping(value = "/{petitionId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long petitionId, @RequestBody CommentDto commentDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        CommentDto comment = commentService.createComment(petitionId, commentDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping(value = "/{petitionId}/comments/{commentId}")
    ResponseEntity<CommentDto> getComment(@PathVariable Long petitionId, @PathVariable Long commentId) {
        CommentDto commentDto = commentService.getComment(petitionId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @PutMapping(value = "/{petitionId}/comments/{commentId}")
    ResponseEntity<CommentDto> changeComment(@PathVariable Long petitionId, @PathVariable Long commentId, @RequestBody CommentDto commentDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        CommentDto comment = commentService.changeComment(petitionId, commentId, commentDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping(value = "/{petitionId}/comments/{commentId}")
    ResponseEntity<?> deleteComment(@PathVariable Long petitionId, @PathVariable Long commentId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        commentService.deleteComment(petitionId, commentId, token);
        return ResponseEntity.noContent().build();
    }
}
