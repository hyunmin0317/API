package com.smunity.api.domain.question.entity;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.global.common.BaseEntity;
import lombok.*;
import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public void update(String subject, String content, Boolean anonymous) {
        this.subject = subject;
        this.content = content;
        this.anonymous = anonymous;
    }
}
