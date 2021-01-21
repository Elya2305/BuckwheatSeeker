package com.progastination.repository;

import com.progastination.entity.Product;
import com.progastination.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByEan(String ean);

    @Query(value = "select distinct p.shops from Product p")
    List<Set<Shop>> getDistinctShops();
}
