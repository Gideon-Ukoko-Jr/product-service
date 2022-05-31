package com.project.productservice.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemResponse {

    private long id;

    private long productId;

    private long customerId;

    private String customerEmail;

    private String productName;

    private BigDecimal unitPrice;

    private int quantity;

    private BigDecimal totalPrice;

}
