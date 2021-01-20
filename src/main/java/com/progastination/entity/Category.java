package com.progastination.entity;

import com.progastination.utils.converter.ShopConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "category")
@ToString(exclude = {"category"})
public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Id
    private String identifier;

    private String title;

//    @JoinColumn(name = "parent_identifier")
    @ManyToOne()
    private Category category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private Set<Category> subCategories;

    private Integer count;

    private String image;

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    @Column(columnDefinition = "TEXT")
    @Convert(converter = ShopConverter.class)
    private Set<Shop> shops = new HashSet<>();

    public void addShop(Shop shop) {
        this.shops.add(shop);
    }
}
