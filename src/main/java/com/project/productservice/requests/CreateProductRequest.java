package com.project.productservice.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class CreateProductRequest {

    @ApiModelProperty(notes = "Product Name", required = true)
    @NotEmpty
    @NotNull
    private String name;

    @ApiModelProperty(notes = "Product Description", required = true)
    @NotEmpty
    @NotNull
    private String description;

    @ApiModelProperty(notes = "Product Price", required = true)
    @NotEmpty
    @NotNull
    private BigDecimal price;

    @ApiModelProperty(notes = "Product Quantity", required = true)
    @NotEmpty
    @NotNull
    private int availableQuantity;
}
