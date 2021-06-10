package com.eshop.backend.dao.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;

    private String productName;

    private int productAmount;

    private double productPrice;

    private double productDiscount;

    private Date productDate;

    private String productPict;

    private String productDescription;

    private String productStatus;

    private int genre;

    private int coverType;

    private int author;

    private int language;

    private int publisher;

}
