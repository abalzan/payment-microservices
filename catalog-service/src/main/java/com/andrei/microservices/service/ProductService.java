package com.andrei.microservices.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.andrei.microservices.entity.Product;
import com.andrei.microservices.entity.ProductInventoryResponse;
import com.andrei.microservices.repository.ProductRespository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ProductService {

	private final ProductRespository productRespository;
	private final RestTemplate restTemplate;

	public ProductService(ProductRespository productRespository, RestTemplate restTemplate) {
		this.productRespository = productRespository;
		this.restTemplate = restTemplate;
	}

	public List<Product> findAllProducts() {
		return productRespository.findAll();
	}

	public Optional<Product> findProductByCode(String code) {
		Optional<Product> productOptional = productRespository.findByCode(code);
		if(productOptional.isPresent()) {
			log.info("Fetching inventory level for Product code "+code);
			
			ResponseEntity<ProductInventoryResponse> itemResponseEntity = restTemplate.getForEntity("http://inventory-service/api/inventory/{code}",
																									ProductInventoryResponse.class, code);
			if(itemResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
				Integer quantity = itemResponseEntity.getBody().getAvailableQuantity();
				log.info("available quantity is " + quantity);
				productOptional.get().setInStock(quantity > 0);
			} else {
                log.error("Unable to get inventory level for product_code: "+code +
                ", StatusCode: "+itemResponseEntity.getStatusCode());
            }
        }
        return productOptional;
	}

}
