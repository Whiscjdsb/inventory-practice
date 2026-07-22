package com.example.inventorypractice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("stock_operation")

public class StockOperation {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long productId;
    private Integer operationType;
    private Integer quantity;
    private Integer beforeStock;
    private Integer afterStock;
    private LocalDateTime createTime;
}
