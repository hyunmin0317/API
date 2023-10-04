package com.smunity.api.domain.petition.entity;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.global.common.BaseEntity;
import lombok.*;
import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "petitions_petition_voter")
public class Agreement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "petition_id")
    @ToString.Exclude
    private Petition petition;
}
