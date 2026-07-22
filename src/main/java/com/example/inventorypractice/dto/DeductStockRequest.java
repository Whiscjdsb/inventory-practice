package com.example.inventorypractice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeductStockRequest {
    @NotNull(message = "扣减数量不能为空")
    @Positive(message = "扣减数量必须大于0")
    private Integer quantity;
}
