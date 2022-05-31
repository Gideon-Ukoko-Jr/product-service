package com.project.productservice.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    private long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private int availableQuantity;
    private String merchantName;
}
