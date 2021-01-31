package com.opensource.productservice.controller;

import javax.persistence.PostRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.opensource.productservice.dto.Coupon;
import com.opensource.productservice.model.Product;
import com.opensource.productservice.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${couponService.url}")
	private String couponServiceUrl;
	
	@PostMapping("/products")
	public Product create(@RequestBody Product product) {
		ResponseEntity<Coupon> coupon = restTemplate.getForEntity(couponServiceUrl+product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getBody().getDiscount()));
		return productRepo.save(product);
	}
	
	@GetMapping("/products/{name}")
	public Product getProduct(@PathVariable String name) {
		return productRepo.findByName(name);
	}
	
}
