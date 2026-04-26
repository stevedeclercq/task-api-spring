package com.mycompany.task.service;

import com.mycompany.task.entity.Customer;
import com.mycompany.task.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo){
        this.repo=repo;
    }

    public List<Customer>findAll(){
        return repo.findAll();
    }

    public Optional<Customer>findById(Long id){
        return repo.findById(id);
    }

    public Customer save(Customer customer){
        return repo.save(customer);
    }

    public boolean deleteById(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
