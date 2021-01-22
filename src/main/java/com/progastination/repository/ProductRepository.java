package com.progastination.repository;

import com.progastination.entity.Category;
import com.progastination.entity.Product;
import com.progastination.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, String>, PagingAndSortingRepository<Product, String> {

    Optional<Product> findByEan(String ean);

    @Query(value = "select p from Product p where p.category.identifier=:category")
    Page<Product> findAllByCategory(@Param("category") String category, Pageable pageable);

    @Query(value = "select p from Product p where p.category.identifier=:category and p.shop=:shop")
    Page<Product> findAllByCategoryAndShop(String category, String shop, Pageable pageableUnsorted);
}
