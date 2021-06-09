package com.eshop.backend.DAO.DataAccess.Product;

import com.eshop.backend.DAO.Models.Product;
import com.eshop.backend.product.catalog.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Product product) {
        String SQL = "insert into product (productname, productamount,\n" +
                "                     productprice, productdiscount,\n" +
                "                     productdate, productdescription,\n" +
                "                     productstatus, productcategory)\n" +
                "                     values (?,?,?,?,?,?,?,?)";
        try {
            template.update(SQL, product.getProductName(), product.getProductAmount(),
                    product.getProductPrice(), product.getProductDiscount(),
                    product.getProductDate(), product.getProductDescription(),
                    product.getProductStatus(), product.getProductCategory());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getProductPage(int page, int size) {
        String sql = ProductMapper.SELECT_SQL +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return template.query(sql, new ProductMapper());

    }

    @Override
    public void update(Product product) {
//        String SQL = "update product set productname = ?,\n" +
//                "                   productamount= ?,\n" +
//                "                   productprice = ?,\n" +
//                "                   productdiscount = ?,\n" +
//                "                   productdate = ?,\n" +
//                "                   productdescription = ?,\n" +
//                "                   productstatus = ?,\n" +
//                "                   productcategory = ?\n" +
//                "                   where id = ?";
//        try {
//            template.update(SQL, product.getProductName(), product.getProductAmount(),
//                    product.getProductPrice(), product.getProductDiscount(),
//                    product.getProductDate(), product.getProductDescription(),
//                    product.getProductStatus(), product.getProductCategory(), product.getId());
//        } catch (Exception e) {
//            String str = e.toString();
//        }
    }

    @Override
    public void delete(Long id) {
//        try {
//            String SQL = "delete from product where id = ?";
//            template.update(SQL, id);
//        } catch (Exception e) {
//            String str = e.toString();
//        }
    }

    public void remove(Long id) {
//        String SQL = "update product set \"productStatus\" = false where id = ?";
//        template.update(SQL, id);
    }

    @Override
    public List<Product> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
        String inSql = String.join(",", Collections.nCopies(filter.size(), "?"));
        String sql = String.format(ProductMapper.SELECT_SQL +
                " left join productcategory on p.productcategory = productcategory.id" +
                " where productcategory.productcategoryname in (%s) order by p." + orderBy +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY", inSql);
        return template.query(sql, new ProductMapper(), filter.toArray());
    }

    @Override
    public List<Product> getAllOrderBy(int page, int size, String orderBy) {
        String sql = ProductMapper.SELECT_SQL + " order by p." + orderBy +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return template.query(sql, new ProductMapper());
    }

}
