package com.andrei.microservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrei.microservices.entity.InventoryItem;

public interface InventoryItemRepository  extends JpaRepository<InventoryItem, Long>{

    Optional<InventoryItem> findByProductCode(String productCode);
}
