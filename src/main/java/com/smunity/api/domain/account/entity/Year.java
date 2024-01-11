package com.smunity.api.domain.account.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "accounts_year")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String year;

    private int major_i;

    private int major_s;

    private int culture;

    private int culture_cnt;

    private int all;

    @OneToMany(mappedBy = "year")
    private List<Profile> profiles;
}
