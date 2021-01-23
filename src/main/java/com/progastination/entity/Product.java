package com.progastination.entity;

import com.progastination.dto.ProducerDto;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

// todo add unit
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
    private String title;
    private String webUrl;
    private Integer weight;
    @ManyToOne
    private Category category; // parentCategoryId

    @MapKeyEnumerated(EnumType.STRING)
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Map<Shop, Integer> prices = new HashMap<>();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ProducerDto producer;
}
