package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CommentService {
    List<CommentDto.Response> getCommentsByPetitionId(Long petitionId);
    CommentDto.Response createComment(Long petitionId, CommentDto.Request commentDto, String token);
    CommentDto.Response getCommentById(Long petitionId, Long commentId);
    @Transactional
    CommentDto.Response updateComment(Long petitionId, Long commentId, CommentDto.Request commentDto, String token);
    void deleteComment(Long petitionId, Long commentId, String token);
}
