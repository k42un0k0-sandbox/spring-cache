package com.example.demo.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(AuthorityPK.class)
public class AuthorityDTO {

    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    String username;

    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    String authority;

}
