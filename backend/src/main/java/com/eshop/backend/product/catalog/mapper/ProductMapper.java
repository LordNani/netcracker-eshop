package com.eshop.backend.product.catalog.mapper;

import com.eshop.backend.dao.Models.AuthorizedUser;
import com.eshop.backend.dao.Models.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    public static final String SELECT_SQL = "select p.id, p.productname, " +
            "p.productamount, p.productprice, p.productdiscount, p.productdate, " +
            "p.productpict, p.productdescription, p.productstatus, " +
            "p.genre, p.covertype, p.author, p.language, p.publisher from product p ";

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("productname");
        Long amount = rs.getLong("productamount");
        int price = rs.getInt("productprice");
        int discount = rs.getInt("productdiscount");
        Date date = rs.getDate("productdate");
        String pict = rs.getString("productpict");
        String description = rs.getString("productdescription");
        String status = rs.getString("productstatus");
        Long genre = rs.getLong("genre");
        Long coverType = rs.getLong("covertype");
        Long author = rs.getLong("author");
        Long language = rs.getLong("language");
        Long publisher = rs.getLong("publisher");
        return new Product(Math.toIntExact(id), name, Math.toIntExact(amount), price, discount, date, pict, description, status,
                Math.toIntExact(genre),Math.toIntExact(coverType), Math.toIntExact(author), Math.toIntExact(language), Math.toIntExact(publisher));
    }
}
