package com.smunity.api.domain.petition.domain;

import com.smunity.api.domain.account.domain.User;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
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

}
