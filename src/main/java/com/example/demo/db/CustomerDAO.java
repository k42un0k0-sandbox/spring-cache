package com.example.demo.db;

import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<CustomerDTO, String> {
}
