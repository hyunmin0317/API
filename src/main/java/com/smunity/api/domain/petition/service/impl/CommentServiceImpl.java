package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.service.CommentService;
import java.util.List;


public class CommentServiceImpl implements CommentService {
    @Override
    public List<CommentDto> findAllComments(Long petitionId) {
        return null;
    }

    @Override
    public CommentDto getComment(Long petitionId, Long commentId) {
        return null;
    }

    @Override
    public CommentDto createComment(Long petitionId, CommentDto commentDto, String token) {
        return null;
    }

    @Override
    public CommentDto changeComment(Long petitionId, Long commentId, CommentDto commentDto, String token) {
        return null;
    }

    @Override
    public void deleteComment(Long petitionId, Long commentId, String token) {

    }
}
