package com.progastination.entity;

import javax.persistence.Id;
import java.util.List;

public class Product {

    @Id
    private String id;

    private String category_id;

    private String ean;

    private List<String> gallery;

    private List<String> img;

    private Integer price;

    private String title;

    private String web_url;

    private Integer weight;

}
