package com.example.demo.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(AuthorityPk.class)
public class AuthorityDto {

    @Getter
    @Setter
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    String username;

    @Getter
    @Setter
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    String authority;

}
