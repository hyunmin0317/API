package com.smunity.api.domain.account.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "accounts_department")
public class Department {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String college;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    private String url;

    @OneToMany(mappedBy = "department")
    private List<Profile> profiles;
}
