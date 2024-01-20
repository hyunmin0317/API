package com.smunity.api.domain.petition.entity;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "petitions_comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User author;

    @ManyToOne
    @JoinColumn(name = "petition_id")
    @ToString.Exclude
    private Petition petition;

    public void update(String content) {
        this.content = content;
    }
}