package com.eshop.backend.auth.exceptions;

import com.eshop.backend.product.dao.models.ProductModel;
import org.springframework.http.HttpStatus;

import java.util.List;

public class OrderCartAmountException extends WebException {
    public OrderCartAmountException() {
        super("<h4>Amount problem,</h4>\n" +
                "<p>Some of your product is out of stock " +
                "please check all product and change amount</p>\n");
    }

    public OrderCartAmountException(List<Object> problemProducts) {
        super("OrderCartAmountException", problemProducts);
    }
}
