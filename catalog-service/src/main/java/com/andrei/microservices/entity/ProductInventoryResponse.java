package com.andrei.microservices.entity;

import lombok.Data;

@Data
public class ProductInventoryResponse {
    private String productCode;
    private int availableQuantity;
}