package com.smunity.api.domain.question.domain;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.global.common.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@Table(name = "qna_question")
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean anonymous;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User author;

    @Builder
    public Question(LocalDateTime create_date, LocalDateTime modify_date, Long id, String subject, String content, boolean anonymous, User author) {
        super(create_date, modify_date);
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.anonymous = anonymous;
        this.author = author;
    }
}
