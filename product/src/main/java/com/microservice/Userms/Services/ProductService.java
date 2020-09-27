package com.microservice.Userms.Services;

import java.util.List;
import java.util.Optional;

import com.microservice.Userms.model.Product;


public interface ProductService {
	List<Product> getAllProduct();
	Optional<Product> getSingleProduct(Long productId);
	void createProduct(Product product);
	Object updateProduct();
}
