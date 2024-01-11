package com.smunity.api.domain.account.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "accounts_year")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String year;

    @Column
    private int major_i;

    @Column
    private int major_s;

    @Column
    private int culture;

    @Column
    private int culture_cnt;

    @Column
    private int all;
}
