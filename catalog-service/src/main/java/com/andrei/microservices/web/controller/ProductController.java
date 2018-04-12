package com.andrei.microservices.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrei.microservices.entity.Product;
import com.andrei.microservices.exception.ProductNotFoundException;
import com.andrei.microservices.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	

	@GetMapping
	public List<Product> getAllProducts(){
		return productService.findAllProducts();
	}
	
	@GetMapping("/{code}")
	public Product getProductByCode(@PathVariable String code) {
		return productService.findProductByCode(code)
				.orElseThrow(() -> new ProductNotFoundException("Product with code "+code+" does not exist!!!"));
	}
	
}
