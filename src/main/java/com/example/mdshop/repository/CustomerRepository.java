package com.example.mdshop.repository;

import com.example.mdshop.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    @Query("select c from Customer c where c.firstName like %:name% or c.lastName like %:name%")
    List<Customer> findByName(String name);
    @Modifying
    @Transactional
    @Query("update Customer c set c.blocked=:enabled where c.id=:id")
    int changeEnabled(int id,boolean enabled);
}
