package com.vn.model;

import lombok.Data;

import java.util.Date;

@Data
public class Product {

    private String productId;

    private String name;

    private long price;

    private String image;

    private long quantity;

    private Date createDate;

    private String categoryId;

    private int numberChapter;

    private String author;
}
