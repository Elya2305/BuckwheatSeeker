package com.progastination.repository;

import com.progastination.entity.Category;
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

public interface CategoryRepository extends JpaRepository<Category, String>, PagingAndSortingRepository<Category, String> {

    Optional<Category> findByIdentifier(String identifier);

    Page<Category> findAllByCategoryIsNull(Pageable pageable);

    @Query("select c from Category c where c.category.identifier=:parentIdentifier")
    Page<Category> findSubCategories(@Param("parentIdentifier") String parentIdentifier, Pageable pageable);

}
