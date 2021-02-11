package com.example.spring_cache.db;

import org.springframework.data.repository.CrudRepository;

public interface AuthorityDao extends CrudRepository<AuthorityDto, AuthorityPk> {
    public AuthorityDto findByUsername(String username);
}
