package com.project.productservice.services;


import com.project.productservice.requests.CreateCustomerRequest;

public interface CustomerService {

    String createCustomer(CreateCustomerRequest request);
}
