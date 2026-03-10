package com.example.lab.service;

import com.example.lab.exception.ResourceNotFoundException;
import com.example.lab.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CustomerService {

    Map<String, Customer> customers = new HashMap<>();

    public Customer create(String email, String name, Integer age, String address){
        Customer customer = new Customer(email,name,age,address);
        customers.put(email,customer);
        return customer;
    }
    public List<Customer> findAll(){
        return new ArrayList<>(customers.values());
    }

    public Customer findByEmail(String email){
        Customer customer = customers.get(email);
        if(customer == null){
            throw new ResourceNotFoundException("Customer not found with email: " + email);
        }
        return customer;
    }

    public Customer fullUpdate(String email, String name, Integer age, String address){
        Customer customer =findByEmail(email);
        if(customer == null){
            throw new ResourceNotFoundException("Cannot update. Customer not found with email: " + email);
        }
        customer.setName(name);
        customer.setAge(age);
        customer.setAddress(address);
        return customer;
    }
    public Customer partialUpdate(String email, String name, Integer age, String address){
        Customer customer =findByEmail(email);
        if(customer == null){
            throw new ResourceNotFoundException("Cannot update. Customer not found with email: " + email);
        }
        if(name!=null){
            customer.setName(name);
        }
        if(age!=null)
        {
            customer.setAge(age);
        }
        if(address!=null)
        {
            customer.setAddress(address);
        }
        return customer;
    }

    public void delete(String email){
        if(!customers.containsKey(email)){
            throw new ResourceNotFoundException("Cannot delete. Customer not found with email: " + email);
        }
        customers.remove(email);
    }
}