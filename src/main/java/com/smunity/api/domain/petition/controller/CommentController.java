package com.smunity.api.domain.petition.controller;

import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/petitions/{petitionId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public ResponseEntity<List<CommentDto>> findAllComment(@PathVariable Long petitionId) {
        List<CommentDto> commentDtoList = commentService.findAllComments(petitionId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
    }

    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@PathVariable Long petitionId, @RequestBody CommentDto commentDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        CommentDto comment = commentService.createComment(petitionId, commentDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping(value = "/{commentId}")
    ResponseEntity<CommentDto> getComment(@PathVariable Long petitionId, @PathVariable Long commentId) {
        CommentDto commentDto = commentService.getComment(petitionId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @PutMapping(value = "/{commentId}")
    ResponseEntity<CommentDto> changeComment(@PathVariable Long petitionId, @PathVariable Long commentId, @RequestBody CommentDto commentDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        CommentDto comment = commentService.changeComment(petitionId, commentId, commentDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping(value = "/{commentId}")
    ResponseEntity<?> deleteComment(@PathVariable Long petitionId, @PathVariable Long commentId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        commentService.deleteComment(petitionId, commentId, token);
        return ResponseEntity.noContent().build();
    }
}
