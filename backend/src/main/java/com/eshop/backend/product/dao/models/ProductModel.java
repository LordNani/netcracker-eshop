package com.eshop.backend.product.dao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private Long id;

    private String productName;

    private Long productAmount;

    private double productPrice;

    private double productDiscount;

    private Date productDate;

    private String productPict;

    private String productDescription;

    private String productStatus;

    private Long genre;

    private Long coverType;

    private Long author;

    private Long language;

    private Long publisher;

}
