package com.mycompany.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tag{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;


    public Tag(){}

    public String getLabel(){
        return this.label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){this.id = id;}
}