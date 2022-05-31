package com.project.productservice.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCustomerRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String uniqueCustomerId;
}
