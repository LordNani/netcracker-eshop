package com.eshop.backend.repository.implementations;

import com.eshop.backend.model.OrderProductModel;
import com.eshop.backend.model.ProductModel;
import com.eshop.backend.repository.interfaces.OrderCartDao;
import com.eshop.backend.repository.interfaces.OrderProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class OrderProductDaoImpl implements OrderProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(OrderProductModel model) {

    }

    @Override
    public OrderProductModel getById(Long id) {
        return null;
    }

    @Override
    public List<OrderProductModel> getAll() {
        return null;
    }

    @Override
    public void update(OrderProductModel model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void createOrderProduct(OrderProductModel orderProductModel) {
        String SQL = "insert into orderproduct (productid, ordercardid," +
                "incardproductamount, incardproductprice)\n " +
                "values (?,?,?,?)";

        jdbcTemplate.update(SQL, orderProductModel.getProductId(), orderProductModel.getOrderCardId(),
                orderProductModel.getInCardProductAmount(), orderProductModel.getInCardProductPrice());

    }

    @Override
    public void deleteProductFromOrderCart(Long productId, Long orderCartId) {
        String sql = "delete from orderproduct where productid = ? and ordercardid = ?";
        jdbcTemplate.update(sql, productId, orderCartId);
    }

    @Override
    public void addProductToCart(ProductModel productModel, Long orderCaryId) {
        String sql = "insert into orderproduct (productid, ordercardid, incardproductamount, incardproductprice)\n" +
                "values (?,?,?,?)";
        jdbcTemplate.update(sql, productModel.getId(), orderCaryId, productModel.getProductAmount(),
                productModel.getProductPrice());

    }

    @Override
    public List<ProductModel> getAllProductByOrderId(Long id) {
        try {
            String sql = "select p.productname, p.productdescription, op.incardproductprice, " +
                    "p.productdiscount,\n" +
                    " op.incardproductamount, p.productpict, p.author, p.genre, p.publisher, " +
                    "p.covertype, p.language\n" +
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
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
