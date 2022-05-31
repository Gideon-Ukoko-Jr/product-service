package com.project.productservice.repositories;

import com.project.productservice.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);

    CustomerEntity findByUniqueUserId(String userId);

    boolean existsByEmail(String email);
}
