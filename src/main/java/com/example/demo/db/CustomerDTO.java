package com.example.demo.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerDTO {
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    String username;

    String password;

    @Column(columnDefinition = "boolean default true")
    boolean enabled;
}
