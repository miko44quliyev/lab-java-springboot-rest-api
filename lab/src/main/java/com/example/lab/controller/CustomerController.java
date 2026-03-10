package com.example.lab.controller;


import com.example.lab.model.Customer;
import com.example.lab.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer){
        Customer created= customerService.create(
                customer.getEmail(),
                customer.getName(),
                customer.getAge(),
                customer.getAddress()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/{email}")
    public Customer getByEmail(@PathVariable String email){
        return customerService.findByEmail(email);
    }

    @PutMapping("/{email}")
    public Customer fullUpdate(@Valid @PathVariable String email,
                                   @RequestBody Customer customer){
        return customerService.fullUpdate(
                email,
                customer.getName(),
                customer.getAge(),
                customer.getAddress()
        );
    }
    @PatchMapping("/{email}")
    public Customer partialUpdate( @PathVariable String email,
                                   @RequestBody Customer customer){
        return customerService.fullUpdate(
                email,
                customer.getName(),
                customer.getAge(),
                customer.getAddress()
        );
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email){
        customerService.delete(email);
        return ResponseEntity.noContent().build();
    }
}
