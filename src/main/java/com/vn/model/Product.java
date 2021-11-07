package com.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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
