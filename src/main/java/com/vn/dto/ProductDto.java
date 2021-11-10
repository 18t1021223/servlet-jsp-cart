package com.vn.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDto {

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
