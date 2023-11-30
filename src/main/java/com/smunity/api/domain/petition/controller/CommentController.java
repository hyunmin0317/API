package com.smunity.api.domain.petition.controller;

import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/petitions/{petitionId}/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping()
    public ResponseEntity<Page<CommentDto.Response>> getCommentsByPetitionId(@PageableDefault(size = 5) Pageable pageable, @PathVariable Long petitionId) {
        Page<CommentDto.Response> commentDtoList = commentService.getCommentsByPetitionId(pageable, petitionId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
    }

    @PostMapping()
    public ResponseEntity<CommentDto.Response> createComment(@PathVariable Long petitionId, @RequestBody CommentDto.Request commentDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        CommentDto.Response comment = commentService.createComment(petitionId, commentDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping(value = "/{commentId}")
    ResponseEntity<CommentDto.Response> getCommentById(@PathVariable Long petitionId, @PathVariable Long commentId) {
        CommentDto.Response commentDto = commentService.getCommentById(petitionId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @PutMapping(value = "/{commentId}")
    ResponseEntity<CommentDto.Response> updateComment(@PathVariable Long petitionId, @PathVariable Long commentId, @RequestBody CommentDto.Request commentDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        CommentDto.Response comment = commentService.updateComment(petitionId, commentId, commentDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping(value = "/{commentId}")
    ResponseEntity<?> deleteComment(@PathVariable Long petitionId, @PathVariable Long commentId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        commentService.deleteComment(petitionId, commentId, token);
        return ResponseEntity.noContent().build();
    }
}