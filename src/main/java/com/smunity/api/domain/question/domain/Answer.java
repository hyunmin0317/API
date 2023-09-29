package com.smunity.api.domain.question.domain;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
