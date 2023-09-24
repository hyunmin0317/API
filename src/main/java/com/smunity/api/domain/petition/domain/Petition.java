package com.smunity.api.domain.petition.domain;

import com.smunity.api.domain.account.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@Table(name = "petitions_petition")
public class Petition extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer category;

    @Column(nullable = false)
    private boolean anonymous;

    @Column
    private LocalDateTime end_date;

    @Column(nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User author;

    @Builder
    public Petition(LocalDateTime create_date, LocalDateTime modify_date, Long id, String subject, String content, Integer category, boolean anonymous, LocalDateTime end_date, Integer status, User author) {
        super(create_date, modify_date);
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.category = category;
        this.anonymous = anonymous;
        this.end_date = end_date;
        this.status = status;
        this.author = author;
    }
}
