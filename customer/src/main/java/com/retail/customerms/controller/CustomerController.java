package com.retail.customerms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.retail.customerms.model.CustomerLogin;
import com.retail.customerms.model.CustomerModel;
import com.retail.customerms.service.CustomerServiceImpl;

@Controller
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@GetMapping("/home")
    public ModelAndView home() {

        List<Object> products = customerService.findAllProducts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", products);
        modelAndView.setViewName("home");
        return modelAndView;
    }
	
	@GetMapping("/login")
	public ModelAndView displayLogin() {
		return new ModelAndView("login", "loginData", new CustomerLogin());
	}
	
	@PostMapping("/login")
	public String loginCustomer(@ModelAttribute CustomerLogin loginData, Model model) {
		
		CustomerModel loginResult = customerService.loginCustomer(loginData.getUsername(), loginData.getPassword());
		if (loginResult == null) {
			model.addAttribute("message", "Invalid username and/or password");
			return "redirect:login";
		}
		model.addAttribute("customerData", loginResult);
		return "redirect:/home";
	}
	
	@GetMapping("/signup")
	public ModelAndView displaySignUp() {
		return new ModelAndView("signup", "customerData", new CustomerModel());
	}
	
	@PostMapping("/signup")
	public ModelAndView signUpMapper(@Validated CustomerModel customerData, BindingResult bindingResult) {
		
		
		if (customerService.findByEmail(customerData.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.customerData", "There is already a user registered with the email provided");
        }
        if (customerService.findByUsername(customerData.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "error.customerData", "There is already a user registered with the username provided");
        }
        
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
        	modelAndView.addObject("customerData", new CustomerModel());
        	modelAndView.setViewName("signup");
        } else {
        	customerService.createCustomer(customerData);
        	modelAndView.addObject("successMessage", "User has been registered successfully");
        	modelAndView.addObject("loginData", new CustomerLogin());
            modelAndView.setViewName("login");
        }
		return modelAndView;
	}
	
	@GetMapping("/customers/{customerId}")
	@ResponseBody
	public ResponseEntity<CustomerModel> getSingleCustomer(@PathVariable Long customerId) {
		LOGGER.info("**** UserResource - getting single user ****");
		Optional<CustomerModel> singleCustomer = customerService.getSingleCustomer(customerId);
		
		if (singleCustomer.isPresent()) {
			return ResponseEntity.ok(singleCustomer.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
