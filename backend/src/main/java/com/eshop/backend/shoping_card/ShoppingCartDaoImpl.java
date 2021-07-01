package com.eshop.backend.shoping_card;

import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.product.dao.ProductMapper;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShoppingCartDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    @Override
    public List<ProductModel> getProductsByIds(List<Long> ids) {
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));

        String sql = String.format("select id, productamount from product" +
                " where id in (%s)", inSql);

        RowMapper<ProductModel> rowMapper = (rs, rowNum) -> ProductModel.builder()
                .id(rs.getLong("id"))
                .productAmount(rs.getInt("productamount"))
                .build();
        return jdbcTemplate.query(sql, rowMapper, ids.toArray());

    }

    @Override
    public int getProductsAmountById(Long id) {
        String sql = "SELECT productamount FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

    @Override
    public void createOrderCart(OrderCartModel orderCartModel) {
        String SQL = "insert into ordercart (userid, courierid," +
                "packagedescription, orderstatus, totalprice, username, deliverytime," +
                "fulladdress, dontdisturb)\n" +
                "values (?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, orderCartModel.getUserId(), orderCartModel.getCourierId(),
                orderCartModel.getPackageDescription(), orderCartModel.getOrderStatus(),
                orderCartModel.getTotalPrice(), orderCartModel.getUserName(),
                orderCartModel.getDeliveryTime(), orderCartModel.getFullAddress(),
                orderCartModel.getDontDisturb());

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
    public OrderCartModel getOrderCartByUserId(Long id) {
        try {

            String sql = "SELECT id, userid, courierid, packagedescription, " +
                    "orderstatus, totalprice, username, deliverytime, fulladdress, " +
                    "dontdisturb FROM ordercart where userid = ? order by id desc limit 1;";

            RowMapper<OrderCartModel> rowMapper = (rs, rowNum) -> new OrderCartModel(
                    rs.getLong("id"),
                    rs.getLong("userid"),
                    rs.getLong("courierid"),
                    rs.getString("packagedescription"),
                    rs.getString("orderstatus"),
                    rs.getInt("totalprice"),
                    rs.getString("username"),
                    rs.getDate("deliverytime"),
                    rs.getString("fulladdress"),
                    rs.getBoolean("dontdisturb"));
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void updateOrderCartTotalPrice(Long id, Integer totalPrice) {
        String SQL = "update ordercart set totalprice = ? where id = ?";
        jdbcTemplate.update(SQL, totalPrice, id);
    }
}
