package com.vn.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
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
