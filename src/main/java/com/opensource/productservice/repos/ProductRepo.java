package com.opensource.productservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensource.productservice.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
	Product findByName(String name);

}
