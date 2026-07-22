package com.example.inventorypractice.vo;

import com.example.inventorypractice.entity.StockOperation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockOperationVO {
    private Long id;
    private Long productId;
    private Integer operationType;
    private String operationTypeText;
    private Integer quantity;
    private Integer beforeStock;
    private Integer afterStock;
    private LocalDateTime createTime;


    public static StockOperationVO fromEntity(StockOperation entity) {
        StockOperationVO stockOperationVO = new StockOperationVO();
        stockOperationVO.setId(entity.getId());
        stockOperationVO.setProductId(entity.getProductId());
        stockOperationVO.setOperationType(entity.getOperationType());
        stockOperationVO.setOperationTypeText(entity.getOperationType() == 1 ? "入库" : "出库");
        stockOperationVO.setQuantity(entity.getQuantity());
        stockOperationVO.setBeforeStock(entity.getBeforeStock());
        stockOperationVO.setAfterStock(entity.getAfterStock());
        stockOperationVO.setCreateTime(entity.getCreateTime());
        return stockOperationVO;
    }
}
