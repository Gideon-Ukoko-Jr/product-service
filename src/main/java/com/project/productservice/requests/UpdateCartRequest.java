package com.project.productservice.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateCartRequest {

    @ApiModelProperty(notes = "Cart Item Id", required = true)
    @NotEmpty
    @NotNull
    private long id;

    @ApiModelProperty(notes = "New Quantity", required = true)
    @NotEmpty
    @NotNull
    private int quantity;
}
