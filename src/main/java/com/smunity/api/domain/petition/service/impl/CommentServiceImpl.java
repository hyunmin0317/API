package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.entity.Comment;
import com.smunity.api.domain.petition.entity.Petition;
import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.repository.CommentRepository;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.CommentService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> getCommentsByPetitionId(Long petitionId) {
        List<CommentDto> commentDtoList = commentRepository.findAllByPetitionId(petitionId)
                .stream()
                .map(CommentDto::of)
                .collect(Collectors.toList());
        return commentDtoList;
    }

    @Override
    public CommentDto createComment(Long petitionId, CommentDto commentDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username);
        Comment comment = commentDto.toEntity(user, petition);
        Comment saveComment = commentRepository.save(comment);
        return CommentDto.of(saveComment);
    }

    @Override
    public CommentDto getCommentById(Long petitionId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (comment.getPetition().getId() != petitionId)
            throw new RestException(HttpStatus.BAD_REQUEST);
        return CommentDto.of(comment);
    }

    @Override
    public CommentDto updateComment(Long petitionId, Long commentId, CommentDto commentDto, String token) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(comment.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        if (comment.getPetition().getId() != petitionId)
            throw new RestException(HttpStatus.BAD_REQUEST);
        comment.setContent(commentDto.getContent());
        Comment changedComment = commentRepository.save(comment);
        return CommentDto.of(changedComment);
    }

    @Override
    public void deleteComment(Long petitionId, Long commentId, String token) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RestException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(comment.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        if (comment.getPetition().getId() != petitionId)
            throw new RestException(HttpStatus.BAD_REQUEST);
        commentRepository.delete(comment);
    }
}
