package com.eshop.backend.repository.mappers;

import com.eshop.backend.model.ProductModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<ProductModel> {

    public static final String SELECT_SQL = "select p.id, p.productname, " +
            "p.productamount, p.productprice, p.productdiscount, p.productdate, " +
            "p.productpict, p.productdescription, p.productstatus, " +
            "p.genre, p.covertype, p.author, p.language, p.publisher from product p ";

    @Override
    public ProductModel mapRow(ResultSet rs, int i) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("productname");
        int amount = rs.getInt("productamount");
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
        return new ProductModel(id, name, amount, price, discount, date, pict, description, status,
                genre, coverType, author, language, publisher);
    }
}
