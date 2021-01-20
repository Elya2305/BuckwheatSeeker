package com.progastination.repository;

import com.progastination.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByIdentifier(String identifier);

    List<Category> findAllByCategoryIsNull();

    @Query("select c from Category c where c.category.identifier=:parentIdentifier")
    List<Category> findSubCategories(@Param("parentIdentifier") String parentIdentifier);
}
