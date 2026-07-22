package com.example.inventorypractice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    @NotBlank(message = "名字不能为空")
    @Size(max = 100, message = "名字不能超过100个字符")
    private String name;

    @NotNull(message = "商品价格不能为空")
    @Positive(message = "商品价格必须大于0")
    private BigDecimal price;

    @Min(value =0, message = "库存数量不能小于0")
    private Integer stock;
}
