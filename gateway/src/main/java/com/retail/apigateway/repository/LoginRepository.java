package com.retail.apigateway.repository;

import org.springframework.data.repository.CrudRepository;

import com.retail.apigateway.model.LoginModel;

public interface LoginRepository extends CrudRepository<LoginModel, Long> {

}
