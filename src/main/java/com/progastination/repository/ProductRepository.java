package com.progastination.repository;

import com.progastination.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String>, PagingAndSortingRepository<Product, String> {

    Optional<Product> findByEan(String ean);

    @Query(value = "select p from Product p")
    Page<Product> findAllPageable(Pageable pageable);

    @Query(value = "select p from Product p where p.category.identifier=:category order by p.price asc")
    Page<Product> findAllByCategory(@Param("category") String category, Pageable pageable);

    @Query(value = "select p from Product p where p.category.identifier=:category and p.shop=:shop order by p.price asc ")
    Page<Product> findAllByCategoryAndShop(String category, String shop, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.title LIKE CONCAT('%',:search,'%')")
        // todo add or producer
    Page<Product> findByNameOrProducer(@Param("search") String search, Pageable pageable);
}
