package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.CommentDto;
import java.util.List;


public interface CommentService {
    List<CommentDto> getCommentsByPetitionId(Long petitionId);
    CommentDto createComment(Long petitionId, CommentDto commentDto, String token);
    CommentDto getCommentById(Long petitionId, Long commentId);
    CommentDto updateComment(Long petitionId, Long commentId, CommentDto commentDto, String token);
    void deleteComment(Long petitionId, Long commentId, String token);
}
