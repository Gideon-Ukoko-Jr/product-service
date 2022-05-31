package com.project.productservice.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GetCartItemResponse {

    private long id;
    private long customerId;
    private long productId;
    private String customerEmail;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
