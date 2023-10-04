package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.entity.Comment;
import com.smunity.api.domain.petition.entity.Petition;
import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.repository.CommentRepository;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.CommentService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> getCommentsByPetitionId(Long petitionId) {
        List<Comment> commentList = commentRepository.findAllByPetitionId(petitionId);
        return CommentDto.toDtos(commentList);
    }

    @Override
    public CommentDto createComment(Long petitionId, CommentDto commentDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username);
        Comment comment = commentDto.toEntity(user, petition);
        Comment saveComment = commentRepository.save(comment);
        return CommentDto.toDto(saveComment);
    }

    @Override
    public CommentDto getCommentById(Long petitionId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        if (comment.getPetition().getId() != petitionId)
            throw new CustomException(HttpStatus.BAD_REQUEST);
        return CommentDto.toDto(comment);
    }

    @Override
    public CommentDto updateComment(Long petitionId, Long commentId, CommentDto commentDto, String token) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(comment.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        if (comment.getPetition().getId() != petitionId)
            throw new CustomException(HttpStatus.BAD_REQUEST);
        comment.setContent(commentDto.getContent());
        Comment changedComment = commentRepository.save(comment);
        return CommentDto.toDto(changedComment);
    }

    @Override
    public void deleteComment(Long petitionId, Long commentId, String token) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(comment.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        if (comment.getPetition().getId() != petitionId)
            throw new CustomException(HttpStatus.BAD_REQUEST);
        commentRepository.delete(comment);
    }
}
