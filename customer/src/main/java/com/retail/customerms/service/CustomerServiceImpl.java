package com.retail.customerms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.retail.customerms.model.CustomerModel;
import com.retail.customerms.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Value("${productms.url}")
	private String productmsUrl;

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
	public List<CustomerModel> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<CustomerModel> getSingleCustomer(Long customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public Object getProduct() {
		// TODO Auto-generated method stub
		return null;
	}

}
