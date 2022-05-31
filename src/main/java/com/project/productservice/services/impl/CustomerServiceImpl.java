package com.project.productservice.services.impl;

import com.project.productservice.entities.CustomerEntity;
import com.project.productservice.repositories.CustomerRepository;
import com.project.productservice.requests.CreateCustomerRequest;
import com.project.productservice.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public String createCustomer(CreateCustomerRequest request) {

        if (!customerRepository.existsByEmail(request.getEmail())){
            CustomerEntity customer = CustomerEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .uniqueUserId(request.getUniqueCustomerId())
                    .createdTime(LocalDateTime.now())
                    .build();

            customerRepository.save(customer);
            return "done";
        }

        return "not done";
    }
}
