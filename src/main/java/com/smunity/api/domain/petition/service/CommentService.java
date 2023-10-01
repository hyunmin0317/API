package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.dto.RespondDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAllComments(Long petitionId);
    CommentDto getComment(Long petitionId, Long commentId);
    CommentDto createComment(Long petitionId, CommentDto commentDto, String token);
    CommentDto changeComment(Long petitionId, Long commentId, CommentDto commentDto, String token);
    void deleteComment(Long petitionId, Long commentId, String token);
}
