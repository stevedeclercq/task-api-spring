package com.mycompany.task.service;

import com.mycompany.task.entity.Address;
import com.mycompany.task.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repo;

    public AddressService(AddressRepository repo){
        this.repo=repo;
    }

    public List<Address>findAll(){
        return repo.findAll();
    }

    public Optional<Address>findById(Long id){
        return repo.findById(id);
    }

    public Address save (Address address){
        return repo.save(address);
    }

    public void deleteById(Long id){
         repo.deleteById(id);
    }
}
