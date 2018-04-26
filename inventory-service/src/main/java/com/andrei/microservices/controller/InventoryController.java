package com.andrei.microservices.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.andrei.microservices.entity.InventoryItem;
import com.andrei.microservices.repository.InventoryItemRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InventoryController {

	private InventoryItemRepository inventoryItemRepository;

	public InventoryController(InventoryItemRepository inventoryItemRepository) {
		this.inventoryItemRepository = inventoryItemRepository;
	}

	@GetMapping("/api/inventory/{productCode}")
	public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable("productCode") String productCode) {
		log.info("Finding inventory for product code :" + productCode);
		Optional<InventoryItem> inventoryItem = inventoryItemRepository.findByProductCode(productCode);
		if (inventoryItem.isPresent()) {
			return new ResponseEntity(inventoryItem, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

}
