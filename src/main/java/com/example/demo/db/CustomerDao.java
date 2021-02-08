package com.example.demo.db;

import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<CustomerDto, Long> {
    CustomerDto findByUsername(String username);
}
