package com.example.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productmanager.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { }