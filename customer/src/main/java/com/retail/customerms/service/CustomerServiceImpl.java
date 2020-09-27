package com.retail.customerms.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.retail.customerms.model.CustomerModel;
import com.retail.customerms.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Value("${productms.all-products}")
	private String allProductsUrl;

	@Override
	public void createCustomer(CustomerModel customer) {
		customerRepository.save(customer);
		
	}

	@Override
	public void updateCustomer(CustomerModel customer) {
		
		customerRepository.updateCustomer(customer.getFirstName(), customer.getLastName(), customer.getAge(), customer.getAddress(), customer.getId());
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
		
	}
	
	@Override
	public Optional<CustomerModel> findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public Optional<CustomerModel> findByUsername(String username) {
		return customerRepository.findByUsername(username);
	}
	
	@Override
	public CustomerModel loginCustomer(String username, String password) {
		
		CustomerModel customer = customerRepository.findByUsername(username).get();
		if (!customer.getPassword().equals(password))	
			return null;
		return customer;
	}

	@Override
	public List<CustomerModel> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<CustomerModel> getSingleCustomer(Long customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public Object getProduct() {
		return null;
//		return restTemplate.getForObject(allProductsUrl, Object.class);
	}

	@Override
	public List<Object> findAllProducts() {
		
		ResponseEntity<Object[]> response = restTemplate.getForEntity(allProductsUrl, Object[].class);
		return Arrays.asList(response.getBody());
	}
}
