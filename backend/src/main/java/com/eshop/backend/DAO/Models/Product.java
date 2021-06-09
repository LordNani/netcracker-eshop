package com.eshop.backend.DAO.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;

    private int productCategory;

    private String productName;

    private int productAmount;

    private double productPrice;

    private double productDiscount;

    private Date productDate;

    private String productPict;

    private String productDescription;

    private String productStatus;

}
