package com.project.productservice.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class GetCartItemResponseWithTotalAmount {

    private List<GetCartItemResponse> itemResponses;

    private BigDecimal totalAmount;

}
