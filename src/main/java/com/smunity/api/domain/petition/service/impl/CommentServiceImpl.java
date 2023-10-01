package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.domain.Comment;
import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.domain.Respond;
import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.dto.RespondDto;
import com.smunity.api.domain.petition.repository.CommentRepository;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.CommentService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PetitionRepository petitionRepository, CommentRepository commentRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.petitionRepository = petitionRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDto> findAllComments(Long petitionId) {
        List<Comment> commentList = commentRepository.findAllByPetitionId(petitionId);
        return CommentDto.toDtos(commentList);
    }

    @Override
    public CommentDto getComment(Long petitionId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        if (comment.getPetition().getId() != petitionId)
            throw new CustomException(HttpStatus.BAD_REQUEST);
        return CommentDto.toDto(comment);
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
        CommentDto commentResponseDto = CommentDto.toDto(saveComment);
        return commentResponseDto;
    }

    @Override
    public CommentDto changeComment(Long petitionId, Long commentId, CommentDto commentDto, String token) {
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
