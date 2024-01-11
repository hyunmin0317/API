package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {
    Page<CommentDto.Response> getCommentsByPetitionId(Pageable pageable, Long petitionId);

    CommentDto.Response createComment(Long petitionId, CommentDto.Request commentDto, String token);

    CommentDto.Response getCommentById(Long petitionId, Long commentId);

    @Transactional
    CommentDto.Response updateComment(Long petitionId, Long commentId, CommentDto.Request commentDto, String token);

    void deleteComment(Long petitionId, Long commentId, String token);
}
