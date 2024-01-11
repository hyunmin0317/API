package com.smunity.api.domain.question.entity;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.global.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qna_question")
public class Question extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean anonymous;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToOne(mappedBy = "question", fetch = FetchType.LAZY)
    private Answer answer;

    public void update(String subject, String content, Boolean anonymous) {
        this.subject = subject;
        this.content = content;
        this.anonymous = anonymous;
    }
}
