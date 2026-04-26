package com.mycompany.task.controller;

import com.mycompany.task.entity.Supplier;
import com.mycompany.task.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController( SupplierService service){
        this.service=service;
    }

    @GetMapping
    public List<Supplier> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier>findById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Supplier save(@RequestBody Supplier supplier){
        return service.save(supplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(service.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
