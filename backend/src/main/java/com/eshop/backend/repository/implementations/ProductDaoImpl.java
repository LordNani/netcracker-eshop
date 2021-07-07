package com.eshop.backend.repository.implementations;

import com.eshop.backend.model.FilterModel;
import com.eshop.backend.model.ProductModel;
import com.eshop.backend.repository.interfaces.ProductDao;
import com.eshop.backend.repository.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(ProductModel productModel) {
        String SQL = "insert into product (productname, productamount,\n" +
                "                     productprice, productdiscount,\n" +
                "                     productdate, productpict, productdescription,\n" +
                "                     productstatus, genre, " +
                "                     covertype, author," +
                "                     language, publisher)\n" +
                "                     values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(SQL, productModel.getProductName(), productModel.getProductAmount(),
                productModel.getProductPrice(), productModel.getProductDiscount(),
                new Date(System.currentTimeMillis()), productModel.getProductPict(), productModel.getProductDescription(),
                productModel.getProductStatus(), productModel.getGenre(),
                productModel.getCoverType(), productModel.getAuthor(),
                productModel.getLanguage(), productModel.getPublisher());

    }

    @Override
    public ProductModel getById(Long id) {
        String sql = ProductMapper.SELECT_SQL + " where id = ?";
        try {
            return template.queryForObject(sql, new ProductMapper(), new Object[]{Long.valueOf(id)});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ProductModel> getAll() {
        return null;
    }

//    @Override
//    public List<ProductModel> getProductPage(int page, int size) {
//        String sql = ProductMapper.SELECT_SQL +
//                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
//        return template.query(sql, new ProductMapper());
//    }

    @Override
    public void update(ProductModel productModel) {
        String SQL = "update product set productname = ?,\n" +
                "                   productamount= ?,\n" +
                "                   productprice = ?,\n" +
                "                   productdiscount = ?,\n" +
                "                   productdate = ?,\n" +
                "                   productpict = ?,\n" +
                "                   productdescription = ?,\n" +
                "                   productstatus = ?,\n" +
                "                   genre = ?,\n" +
                "                   covertype = ?,\n" +
                "                   author = ?,\n" +
                "                   language = ?,\n" +
                "                   publisher = ? \n" +
                "                   where id = ?";
        template.update(SQL, productModel.getProductName(), productModel.getProductAmount(),
                productModel.getProductPrice(), productModel.getProductDiscount(),
                productModel.getProductDate(), productModel.getProductPict(),
                productModel.getProductDescription(), productModel.getProductStatus(),
                productModel.getGenre(), productModel.getCoverType(),
                productModel.getAuthor(), productModel.getLanguage(),
                productModel.getPublisher(), productModel.getId());
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Integer getNumberOfSearchedOrderedFiltered(String search, String orderBy, FilterModel filterModel, boolean isActive) {
        StringBuilder sql = new StringBuilder("SELECT COUNT (*) from product p  ");
        Object[] paramsForQuery = getSearchedFilteredBuilder(search, filterModel, sql, isActive);
        return template.queryForObject(sql.toString(), Integer.class, paramsForQuery);
    }

    @Override
    public void updateAmountById(Long id, int price) {
        String SQL = "UPDATE product SET productamount = ? WHERE id = ?";
        template.update(SQL, price, id);
    }

    @Override
    public Integer getAmountById(Long id) {
        try {
            String sql = "SELECT productamount FROM product where id = ?";
            return template.queryForObject(sql, Integer.class, id);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public String getStatusById(Long id) {
        String sql = "SELECT productstatus FROM product where id = ?";
        try {
            return template.queryForObject(sql, String.class, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ProductModel> getProductsByUserIdAndStatus(Long id) {
        String sql = "select productid, incardproductamount from orderproduct as op\n" +
                "inner join ordercart as oc on oc.id = op.ordercardid\n" +
                "where oc.userid = ? and orderstatus in ('RESERVED', 'UNRESERVED')";

        RowMapper<ProductModel> rowMapper = (rs, rowNum) -> ProductModel.builder()
                .id(rs.getLong("productid"))
                .productAmount(rs.getInt("incardproductamount"))
                .build();

        return template.query(sql, rowMapper, id);

    }

    @Override
    public List<ProductModel> getProductPage(int page, int size) {
        return null;
    }

    @Override
    public List<ProductModel> getSearchedOrderedFiltered(int page, int size, String search, String orderBy, FilterModel filterModel, boolean isActive) {
        StringBuilder sql = new StringBuilder(ProductMapper.SELECT_SQL); //select ... from product p
        Object[] paramsForQuery = getSearchedFilteredBuilder(search, filterModel, sql, isActive); //create main part of query and Object[] of params
        if (!orderBy.equals("")) { //append order by
            sql.append(" order by " + orderBy);
        }
        sql.append(" OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY");
        try{
            return template.query(sql.toString(), new ProductMapper(), paramsForQuery);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getCategoriesOfProduct(int author, int coverType, int genre, int language, int publisher) {
        List<String> categories = new ArrayList<>();
        categories.add(getAuthorById(author));
        categories.add(getCoverTypeById(coverType));
        categories.add(getGenreById(genre));
        categories.add(getLanguageById(language));
        categories.add(getPublisherById(publisher));
        return categories;
    }

    @Override
    public List<ProductModel> getPopular(int page, int size) {
        String sql = ProductMapper.SELECT_SQL +
                "WHERE p.productamount > 0 AND p.id IN (SELECT productid FROM orderproduct " +
                "INNER JOIN ordercart ON orderproduct.ordercardid = ordercart.id " +
                "WHERE ordercart.orderstatus = 'DELIVERED' " +
                "GROUP BY productid " +
                "ORDER BY SUM(incardproductamount) DESC " +
                " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY) ";
        try {
            return template.query(sql, new ProductMapper(), new Object[]{(page - 1), size});
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProductModel> getNew(int page, int size) {
        String sql = ProductMapper.SELECT_SQL + " WHERE p.productstatus = 'ACTIVE' AND p.productamount > 0 order by p.productdate desc " +
                " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            return template.query(sql, new ProductMapper(), new Object[]{(page - 1), size});
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProductModel> getProductInShoppingCart(Long userId) {
        String sql = "select p.id, p.productname, op.incardproductamount, p.productdescription,\n" +
                "p.productdiscount, p.productpict, p.productprice, p.productstatus from product as p\n" +
                "inner join orderproduct as op on p.id = op.productid\n" +
                "inner join ordercart oc on oc.id = op.ordercardid\n" +
                "where oc.userid = ? and oc.orderstatus = 'RESERVED' or oc.orderstatus = 'UNRESERVED'";

        RowMapper<ProductModel> rowMapper = (rs, rowNum) -> ProductModel.builder()
                .id(rs.getLong("id"))
                .productName(rs.getString("productname"))
                .productAmount(rs.getInt("incardproductamount"))
                .productDescription(rs.getString("productdescription"))
                .productDiscount(rs.getInt("productdiscount"))
                .productPict(rs.getString("productpict"))
                .productPrice(rs.getInt("productprice"))
                .productStatus(rs.getString("productstatus"))
                .build();
        try {
            return template.query(sql, rowMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }

    }

    private String getAuthorById(int id) {
        String sql = "SELECT authorname from author where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private String getCoverTypeById(int id) {
        String sql = "SELECT covertypename from covertype where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private String getGenreById(int id) {
        String sql = "SELECT genrename from genre where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private String getLanguageById(int id) {
        String sql = "SELECT languagename from language where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private String getPublisherById(int id) {
        String sql = "SELECT publishername from publisher where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    // creates query and Object [] that contains params for query for search, filters by categories and active (if needed) products
    private Object[] getSearchedFilteredBuilder(String search, FilterModel filterModel, StringBuilder sql, boolean isActive) {
        Object[] paramsForQuery;
        String paramLike = "";
        ArrayList<Long> paramsFilter = new ArrayList<>();
        int paramsForQueryLength = 0;
        int paramsForQueryIterator = 0;

        // search by product name
        if (!search.equals("")) {
            sql.append(" WHERE p.productname ILIKE ? ");
            paramLike = "%" + search + "%";
            paramsForQueryLength++; // Enlarge length of future Object[] for params
        }

        // get only active products for buyer
        if (isActive) {
            if (search.equals("")) {
                sql.append(" WHERE p.productstatus = 'ACTIVE' AND p.productamount > 0 ");
            } else {
                sql.append(" AND p.productstatus = 'ACTIVE' AND p.productamount > 0 ");
            }
        }

        // select filtered
        if ((filterModel.getAuthor().length != 0) ||
                (filterModel.getGenre().length != 0) ||
                (filterModel.getLanguage().length != 0) ||
                (filterModel.getPublisher().length != 0) ||
                (filterModel.getCoverType().length != 0)) { // check if at least one filter is selected
            if (search.equals("") && (!isActive)) { //if both search by name and active filters were not used
                sql.append(" WHERE ");
            } else {
                sql.append(" AND ");
            }
            sql.append(filterSqlBuilder(filterModel, paramsFilter)); // Build sql with selected filters by categories
            paramsForQueryLength += paramsFilter.size(); // Enlarge length of future Object[] for params
        }

        paramsForQuery = new Object[paramsForQueryLength];

        // checks to append only selected params
        if (!paramLike.equals("")) {
            paramsForQuery[paramsForQueryIterator] = paramLike;
            paramsForQueryIterator++;
        }
        if (paramsFilter.size() != 0) {
            for (int i = 0; i < paramsFilter.size(); i++) {
                paramsForQuery[paramsForQueryIterator] = paramsFilter.get(i);
                paramsForQueryIterator++;
            }
        }

        return paramsForQuery;
    }

    // sends info to create IN query
    private String filterSqlBuilder(FilterModel filterModel, ArrayList<Long> params) {
        StringBuilder filters = new StringBuilder();
        filterInBuilder(filters, filterModel.getAuthor(), " p.author ", params);
        filterInBuilder(filters, filterModel.getCoverType(), " p.covertype ", params);
        filterInBuilder(filters, filterModel.getGenre(), " p.genre ", params);
        filterInBuilder(filters, filterModel.getLanguage(), " p.language ", params);
        filterInBuilder(filters, filterModel.getPublisher(), " p.publisher ", params);
        return filters.substring(0, filters.length() - 4); // delete unnecessary AND
    }

    // creates IN query
    private void filterInBuilder(StringBuilder stringBuilder, Long[] foreignKeys, String fkName, ArrayList<Long> params) {
        if (foreignKeys.length != 0) {
            stringBuilder.append(fkName); // append to query name of table which is being referred by a fk
            stringBuilder.append(" IN (");
            for (int i = 0; i < foreignKeys.length; i++) {
                stringBuilder.append("?");
                params.add(foreignKeys[i]);
                if (foreignKeys.length - i > 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(") AND ");
        }
    }

}
