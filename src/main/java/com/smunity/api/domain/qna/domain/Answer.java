package com.smunity.api.domain.qna.domain;

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
@Table(name = "qna_answer")
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User author;

    @OneToOne
    @JoinColumn(name = "question_id")
    @ToString.Exclude
    private Question question;

    @Builder
    public Answer(LocalDateTime create_date, LocalDateTime modify_date, Long id, String content, User author, Question question) {
        super(create_date, modify_date);
        this.id = id;
        this.content = content;
        this.author = author;
        this.question = question;
    }
}
