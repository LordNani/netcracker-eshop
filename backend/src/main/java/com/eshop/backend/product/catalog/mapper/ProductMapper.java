package com.eshop.backend.product.catalog.mapper;

import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.DAO.Models.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    public static final String SELECT_SQL = "select p.id, p.productname, p.productcategory, " +
            "p.productamount, p.productprice, p.productdiscount, p.productdate, " +
            "p.productpict, p.productdescription, p.productstatus " +
            "from product p";

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        Long id = rs.getLong("id");
        int category = rs.getInt("productcategory");
        String name = rs.getString("productname");
        int amount = rs.getInt("productamount");
        double price = rs.getDouble("productprice");
        double discount = rs.getDouble("productdiscount");
        Date date = rs.getDate("productdate");
        String pict = rs.getString("productpict");
        String description = rs.getString("productdescription");
        String status = rs.getString("productstatus");
        return new Product(id, category, name, amount, price, discount, date, pict, description, status);
    }
}
