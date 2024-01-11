package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.entity.Comment;
import com.smunity.api.domain.petition.entity.Petition;
import com.smunity.api.domain.petition.repository.CommentRepository;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.smunity.api.domain.petition.entity.QComment.comment;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final CommentRepository commentRepository;

    public Page<CommentDto.Response> getCommentsByPetitionId(Pageable pageable, Long petitionId) {
        Page<Comment> commentList = commentRepository.findAll(comment.petition.id.eq(petitionId), pageable);
        return CommentDto.Response.from(commentList);
    }

    @Transactional
    public CommentDto.Response createComment(Long petitionId, CommentDto.Request commentDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        Petition petition = petitionRepository.findById(petitionId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        Comment comment = commentDto.toEntity(user, petition);
        Comment saveComment = commentRepository.save(comment);
        return CommentDto.Response.from(saveComment);
    }

    public CommentDto.Response getCommentById(Long petitionId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (comment.getPetition().getId() != petitionId)
            throw new RestException(HttpStatus.BAD_REQUEST);
        return CommentDto.Response.from(comment);
    }

    @Transactional
    public CommentDto.Response updateComment(Long petitionId, Long commentId, CommentDto.Request commentDto, String token) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(comment.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        if (comment.getPetition().getId() != petitionId)
            throw new RestException(HttpStatus.BAD_REQUEST);
        comment.update(commentDto.getContent());
        Comment changedComment = commentRepository.save(comment);
        return CommentDto.Response.from(changedComment);
    }

    @Transactional
    public void deleteComment(Long petitionId, Long commentId, String token) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RestException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(comment.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        if (comment.getPetition().getId() != petitionId)
            throw new RestException(HttpStatus.BAD_REQUEST);
        commentRepository.delete(comment);
    }
}
