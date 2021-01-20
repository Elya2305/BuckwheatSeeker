package com.progastination.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "category")
@ToString(exclude = {"category"})
public class Category {
    @Id
    private String identifier;

    private String title;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Category> subCategories;

    private Integer count;

    private String image;

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(Category category) {
        this.subCategories.add(category);
        category.setCategory(this);
    }

    public void removeSubCategory(Category category) {
        this.subCategories.remove(category);
        category.setCategory(null);
    }
}
