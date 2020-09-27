package com.retail.customerms.service;

import java.util.List;
import java.util.Optional;

import com.retail.customerms.model.CustomerModel;

public interface CustomerService {
	
	void createCustomer(CustomerModel customer);
	
	void updateCustomer(CustomerModel customer);
	
	void deleteCustomer(Long customerId);
	
	Optional<CustomerModel> findByEmail(String email);
	
	Optional<CustomerModel> findByUsername(String username);
	
	CustomerModel loginCustomer(String username, String password);
	
	List<CustomerModel> getAllCustomers();
	
	Optional<CustomerModel> getSingleCustomer(Long customerId);
	
	Object getProduct();
	
	List<Object> findAllProducts();
}
