package com.trainingmug.ecommerce.repository;

import com.trainingmug.ecommerce.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    //This layer will be replaced with db operations

    private List<Customer> customers;

    public CustomerRepository(){
        this.customers = new ArrayList<>();
    }

    public Customer save(Customer customer){
        this.customers.add(customer);
        return customer;
    }
    public List<Customer> findAll(){
        return this.customers;
    }
    public Optional<Customer> findById(int id){
        return this.customers.stream().filter(c -> c.getId() == id).findFirst();
    }
    public Optional<Customer> findByEmail(String email){
        return this.customers.stream().filter(c -> c.getEmail().equals(email)).findFirst();
    }
    public Customer update(Customer customer){
        this.customers.replaceAll(c -> c.getId() == customer.getId() ? customer : c);
        return customer;
    }
    public void deleteById(int id){
        this.customers.removeIf(c -> c.getId() == id);
    }
    public void delete(Customer customer){
        this.customers.remove(customer);
    }


}
