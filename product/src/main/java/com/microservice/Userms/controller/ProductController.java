package com.microservice.Userms.controller;

import java.util.List;
import java.util.Optional;

import com.microservice.Userms.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.Userms.Services.ProductService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductController.class);
	private final ProductService productService;
	private final RestTemplate restTemplate;

	public ProductController(ProductService productService, RestTemplate restTemplate) {
		this.productService = productService;
		this.restTemplate = restTemplate;
	}
	
	@GetMapping("/products")
	public ModelAndView getAllProducts()
	{ LOGGER.info("*** I am in Get All Products Method of Controller Class ***");
		List<Product> list = productService.getAllProduct();
		ModelAndView modelAndView = new ModelAndView("welcome");
		modelAndView.addObject("listOfProducts", list);
		return modelAndView;
	}

	
	@GetMapping("/products/{productId}")
	public ResponseEntity getSingleProduct(@PathVariable Long productId)
	{LOGGER.info("*** I am in Get Product By Id Method In controller class ***");
		Optional<Product> singleProduct= productService.getSingleProduct(productId);
		
		if(singleProduct.isPresent())
		{
			return ResponseEntity.ok(singleProduct.get());
		}
		
		
		return ResponseEntity.notFound().build();
	}



	@RequestMapping("/products/add/{productId}")
	public ModelAndView addProduct(@PathVariable Long productId){
		Product product = productService.getSingleProduct(productId).get();
		ResponseEntity<Product> responseEntity = restTemplate.postForEntity("http://cartms/addProduct", product, Product.class);
		ModelAndView modelAndView = new ModelAndView("success");
		modelAndView.addObject("product", responseEntity.getBody());
		return modelAndView;
	}

	@GetMapping("/carts")
	public ResponseEntity getAllCarts(){

		return restTemplate.getForObject("http://cartms/carts", Object.class) != null ? ResponseEntity.ok(restTemplate.getForObject("http://cartms/carts", Object.class)) : ResponseEntity.notFound().build();
	}
}
