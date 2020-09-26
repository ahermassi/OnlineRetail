package com.retail.customerms.service;

import java.util.List;
import java.util.Optional;

import com.retail.customerms.model.CustomerModel;

public interface CustomerService {
	
	void createCustomer(CustomerModel customer);
	void updateCustomer(CustomerModel customer);
	void deleteCustomer(Long customerId);
	List<CustomerModel> getAllCustomers();
	Optional<CustomerModel> getSingleCustomer(Long customerId);
	Object getProduct();
}
