package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Comment;
import com.smunity.api.domain.petition.entity.Petition;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
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

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.author_id = comment.getAuthor().getId();
        this.petition_id = comment.getPetition().getId();
        this.content = comment.getContent();
        this.create_date = comment.getCreate_date();
        this.modify_date = comment.getModify_date();
    }
}
