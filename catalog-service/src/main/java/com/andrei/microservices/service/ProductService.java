package com.andrei.microservices.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.andrei.microservices.entity.Product;
import com.andrei.microservices.repository.ProductRespository;

@Transactional
@Service
public class ProductService {

	private ProductRespository productRespository;

	public ProductService(ProductRespository productRespository) {
		this.productRespository = productRespository;
	}

	public List<Product> findAllProducts() {
		return productRespository.findAll();
	}

	public Optional<Product> findProductByCode(String code) {
		return productRespository.findByCode(code);
	}

}
