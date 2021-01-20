package com.progastination.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    private String id;

    private String categoryId;

    private String ean;

    private String img;

    private Integer price;

    private String title;

    private String webUrl;

    private Integer weight;

}
