package com.smunity.api.domain.account.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "accounts_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String college;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column
    private String url;
}
