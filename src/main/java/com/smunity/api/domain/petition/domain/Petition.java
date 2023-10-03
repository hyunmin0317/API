package com.smunity.api.domain.petition.domain;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.global.common.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
    private LocalDateTime end_date;

    @Column(nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User author;

    @OneToMany(mappedBy = "petition", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private List<Agreement> agreementList = new ArrayList<>();
}
