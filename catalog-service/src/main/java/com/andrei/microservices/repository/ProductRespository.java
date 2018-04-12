package com.andrei.microservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrei.microservices.entity.Product;

public interface ProductRespository extends JpaRepository<Product, Long> {

	Optional<Product> findByCode(String code);
}
