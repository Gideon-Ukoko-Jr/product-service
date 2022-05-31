package com.project.productservice.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddToCartRequest {

    @ApiModelProperty(notes = "Product Id", required = true)
    @NotEmpty
    @NotNull
    private long productId;

    @ApiModelProperty(notes = "Required Quantity", required = true)
    private int quantity;
}
