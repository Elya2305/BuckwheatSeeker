package com.progastination.entity;

import com.progastination.dto.ProducerDto;
import com.progastination.utils.converter.ShopConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name = "products")
@Data
@Entity
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    private String ean;
    private String categoryId;
    private String image;
    private Integer price;
    private String title;
    private String webUrl;
    private Integer weight;
    @ManyToOne
    private Category category; // parentCategoryId

    @Column(columnDefinition = "TEXT")
    @Convert(converter = ShopConverter.class)
    private Set<Shop> shops = new HashSet<>();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ProducerDto producer;

    public void addShop(Shop shop) {
        this.shops.add(shop);
    }
}
