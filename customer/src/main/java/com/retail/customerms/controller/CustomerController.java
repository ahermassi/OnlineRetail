package com.retail.customerms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.retail.customerms.model.CustomerLogin;
import com.retail.customerms.model.CustomerModel;
import com.retail.customerms.service.CustomerServiceImpl;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@GetMapping("/login")
	public String displayLogin(@ModelAttribute("loginData") CustomerLogin loginData) {
		return "login";
	}
	
	@PostMapping("/login")
	public void loginCustomer(@ModelAttribute CustomerLogin loginData) {
	}
	
	@GetMapping("/signup")
	public String displaySignUp(@ModelAttribute("customerData") CustomerModel customerData) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUpMapper(@ModelAttribute CustomerModel customerData) {
		
		customerService.createCustomer(customerData);
		return "redirect:login";
	}
}
