package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.petition.domain.Comment;
import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.domain.Respond;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class CommentDto {
    private Long id;

    private Long author_id;

    private Long petition_id;

    private String content;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    public Comment toEntity(User user, Petition petition) {
        return Comment.builder()
                .content(content)
                .author(user)
                .petition(petition)
                .build();
    }

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .author_id(comment.getAuthor().getId())
                .petition_id(comment.getPetition().getId())
                .content(comment.getContent())
                .create_date(comment.getCreate_date())
                .modify_date(comment.getModify_date())
                .build();
    }

    public static List<CommentDto> toDtos(List<Comment> commentList) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment: commentList)
            commentDtoList.add(toDto(comment));
        return commentDtoList;
    }
}
