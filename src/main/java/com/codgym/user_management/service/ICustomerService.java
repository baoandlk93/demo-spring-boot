package com.codgym.user_management.service;

import com.codgym.user_management.entity.Customer;

public interface ICustomerService {
    Iterable<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);

}
