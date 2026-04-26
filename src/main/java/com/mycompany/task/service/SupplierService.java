package com.mycompany.task.service;

import com.mycompany.task.entity.Supplier;
import com.mycompany.task.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository repo;

    public SupplierService(SupplierRepository repo){
        this.repo=repo;
    }

    public List<Supplier> findAll(){
        return repo.findAll();
    }

    public Optional<Supplier> findById(Long id){
        return repo.findById(id);
    }

    public Supplier save (Supplier supplier){
        return repo.save(supplier);
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }
}
