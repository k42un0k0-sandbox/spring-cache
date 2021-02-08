package com.example.demo.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class AuthorityPK implements Serializable {
    String username;

    String authority;

    public AuthorityPK(String username, String authority){
        this.username=username;
        this.authority=authority;
    }
}
