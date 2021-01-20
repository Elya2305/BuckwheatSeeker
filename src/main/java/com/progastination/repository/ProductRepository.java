package com.progastination.repository;

import com.progastination.entity.Category;
import com.progastination.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByIdentifier(String identifier);
}
