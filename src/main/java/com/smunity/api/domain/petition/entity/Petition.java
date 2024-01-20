package com.smunity.api.domain.petition.entity;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.enums.Category;
import com.smunity.api.domain.petition.entity.enums.Status;
import com.smunity.api.global.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "petitions_petition")
public class Petition extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(nullable = false)
    private Boolean anonymous;

    @Column
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "petition")
    private List<Agreement> agreements;

    @OneToOne(mappedBy = "petition", fetch = FetchType.LAZY)
    private Respond respond;

    @OneToMany(mappedBy = "petition")
    private List<Comment> comments;

    public void update(String subject, String content, Category category, Boolean anonymous) {
        this.subject = subject;
        this.content = content;
        this.category = category;
        this.anonymous = anonymous;
    }
}
