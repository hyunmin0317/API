package com.smunity.api.domain.petition.entity;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.global.common.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "petitions_petition")
public class Petition extends BaseEntity {
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
    private Boolean anonymous;

    @Column
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User author;

    public void update(String subject, String content, Integer category, Boolean anonymous) {
        this.subject = subject;
        this.content = content;
        this.category = category;
        this.anonymous = anonymous;
    }
}
