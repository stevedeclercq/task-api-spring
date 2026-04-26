package com.mycompany.task.controller;

import com.mycompany.task.entity.Customer;
import com.mycompany.task.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service){
        this.service= service;
    }

    @GetMapping
    public List<Customer>findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer>findById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer){
        return service.save(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        if(service.deleteById(id)){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
