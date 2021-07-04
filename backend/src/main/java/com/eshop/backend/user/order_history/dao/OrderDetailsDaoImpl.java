package com.eshop.backend.user.order_history.dao;

import com.eshop.backend.product.dao.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDetailsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductModel> getAllProductByOrderId(Long id) {
        try {
            String sql = "select p.productname, p.productdescription, op.incardproductprice, p.productdiscount,\n" +
                    "       op.incardproductamount, p.productpict, p.author, p.genre, p.publisher, p.covertype, p.language\n" +
                    "from orderproduct as op\n" +
                    "         inner join ordercart as oc on oc.id = op.ordercardid\n" +
                    "         inner join product as p on p.id = op.productid\n" +
                    "where oc.id = ?";
            RowMapper<ProductModel> rowMapper = (rs, rowNum) -> ProductModel.builder()
                    .productName(rs.getString("productname"))
                    .productDescription(rs.getString("productdescription"))
                    .productPrice(rs.getInt("incardproductprice"))
                    .productDiscount(rs.getInt("productdiscount"))
                    .productAmount(rs.getInt("incardproductamount"))
                    .productPict(rs.getString("productpict"))
                    .author(rs.getLong("author"))
                    .genre(rs.getLong("genre"))
                    .publisher(rs.getLong("publisher"))
                    .coverType(rs.getLong("covertype"))
                    .language(rs.getLong("language"))
                    .build();
            return jdbcTemplate.query(sql, rowMapper, id);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void create(ProductModel model) {

    }

    @Override
    public ProductModel getById(Long id) {
        return null;
    }

    @Override
    public List<ProductModel> getAll() {
        return Collections.emptyList();
    }

    @Override
    public void update(ProductModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
