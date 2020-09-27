package com.microservice.Userms.Services;

import java.util.List;
import java.util.Optional;

import com.microservice.Userms.model.Product;
import com.microservice.Userms.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProductServicesImpl implements ProductService {
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductServicesImpl.class);
	private final ProductRepository productRepository;
	//private final RestTemplate restTemplate;

	
	
	/*public ProductServicesImpl(ProductRepository productRepository, RestTemplate restTemplate) {
		this.productRepository = productRepository;
		this.restTemplate = restTemplate;
	}*/
	public ProductServicesImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProduct() {
		LOGGER.info("*** I am in Get All Product Method ***");
		
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getSingleProduct(Long productId) {
		LOGGER.info("*** I am in Get  User By Id Method ***");
		return productRepository.findById(productId);
	}

	@Override
	public void createProduct(Product product) {
//		productRepository.save(product);

	}

	@Override
	public Object updateProduct() {
		return null;
	}

	/*@Override
	public Object updateProduct() {
		return restTemplate.getForObject("http://cartms/carts", Object.class);
	}*/





}