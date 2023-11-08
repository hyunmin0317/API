package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Comment;
import com.smunity.api.domain.petition.entity.Petition;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class CommentDto {
    @Data
    @Builder
    @ApiModel(value = "CommentDtoResponse")
    public static class Response {
        private Long id;
        private Long author_id;
        private Long petition_id;
        private String content;
        private LocalDateTime create_date;
        private LocalDateTime modify_date;
        public static Response of(Comment comment) {
            return Response.builder()
                    .id(comment.getId())
                    .author_id(comment.getAuthor().getId())
                    .petition_id(comment.getPetition().getId())
                    .content(comment.getContent())
                    .create_date(comment.getCreateDate())
                    .modify_date(comment.getModifyDate())
                    .build();
        }

        public static List<Response> of(List<Comment> commentList) {
            return commentList.stream().map(Response::of).collect(Collectors.toList());
        }
    }

    @Data
    @ApiModel(value = "CommentDtoRequest")
    public static class Request {
        private String content;

        public Comment toEntity(User user, Petition petition) {
            return Comment.builder()
                    .content(content)
                    .author(user)
                    .petition(petition)
                    .build();
        }
    }
}
