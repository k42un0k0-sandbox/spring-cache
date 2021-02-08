package com.example.demo.db;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AuthorityPk implements Serializable {
    String username;

    String authority;

    public AuthorityPk() {

    }

    public AuthorityPk(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
