package com.project.productservice.services.impl;

import com.project.productservice.entities.CustomerEntity;
import com.project.productservice.repositories.CustomerRepository;
import com.project.productservice.services.DefaultAccountSetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultAccountSetupServiceImpl implements DefaultAccountSetupService {

    private final CustomerRepository customerRepository;

    @Override
    public void runSetup() {

        if (customerRepository.existsByEmail("jd@gmail.com")){
            return;
        }

        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("08089727262")
                .email("jd@gmail.com")
                .address("22 Jump Street")
                .build();

        customerRepository.save(customerEntity);

    }
}
