package com.example.inventorypractice.service;

import com.example.inventorypractice.entity.StockOperation;
import com.example.inventorypractice.mapper.StockOperationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockOperationService {

    private final StockOperationMapper stockOperationMapper;

    public StockOperationService(StockOperationMapper stockOperationMapper) {
        this.stockOperationMapper = stockOperationMapper;
    }

    public void recordOperation(Long productId, Integer operationType,
                                Integer quantity, Integer beforeStock,
                                Integer afterStock) {
        StockOperation operation = new StockOperation();
        operation.setProductId(productId);
        operation.setOperationType(operationType);
        operation.setQuantity(quantity);
        operation.setBeforeStock(beforeStock);
        operation.setAfterStock(afterStock);
        operation.setCreateTime(LocalDateTime.now());
        stockOperationMapper.insert(operation);
    }
}