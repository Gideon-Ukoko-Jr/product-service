package com.project.productservice.controllers;

import com.project.productservice.requests.CreateCustomerRequest;
import com.project.productservice.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiIgnore
    @PostMapping("/add")
    public ResponseEntity<String> createCustomer(@RequestBody CreateCustomerRequest request){
        log.info(String.valueOf(request));
        String res = customerService.createCustomer(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
