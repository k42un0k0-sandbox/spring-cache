package com.example.spring_cache.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class CustomerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String username;

    @Setter
    @Getter
    private String password;

    @Getter
    @Column(columnDefinition = "boolean default true")
    boolean enabled;
}
