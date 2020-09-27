package com.retail.customerms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retail.customerms.model.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
	
	Optional<CustomerModel> findByUsername(String username);
	Optional<CustomerModel> findByEmail(String email);
	
	@Modifying
	@Query(value = "UPDATE Customer c SET c.firstName=:firstName, c.lastName=:lastName, c.age=:age, c.address=:address where c.id=:id", nativeQuery = true)
	void updateCustomer(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("age") int age, @Param("address") String address, @Param("id") Long id);

}
