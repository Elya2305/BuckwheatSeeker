package com.progastination.repository;

import com.progastination.entity.Category;
import com.progastination.entity.projection.CategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
/**
* @author Elya
* */

public interface CategoryRepository extends JpaRepository<Category, String>, PagingAndSortingRepository<Category, String> {

    Optional<Category> findByIdentifier(String identifier);

    /**
    * find subcategories of one category
    * */
    @Query(value = "select string_agg(identifier, ',') as identifiers, sum(count) as totalCount, title, max(image) as image, string_agg(shops, ',') as shops,string_agg(category_identifier, ',')" +
            "  from categories where category_identifier in(:categoryIdentifiers)  group by title", nativeQuery = true)
    Page<CategoryProjection> findSubCategories(@Param("categoryIdentifiers") List<String> categoryIdentifiers, Pageable pageable);

    /**
    * count total subcategories of category
    * */
    @Query("select count (c) from Category c where c.category.identifier in (:parentIdentifier)")
    int countChildren(@Param("parentIdentifier") List<String> identifier);

    /**
    * group categories by title
    * */
    @Query(value = "select string_agg(identifier, ',') as identifiers, sum(count) as totalCount, title, max(image) as image, string_agg(shops, ',') as shops,string_agg(category_identifier, ',')" +
            "  from categories where category_identifier is null group by title", nativeQuery = true)
    Page<CategoryProjection> mainCategories(Pageable pageable);
}
