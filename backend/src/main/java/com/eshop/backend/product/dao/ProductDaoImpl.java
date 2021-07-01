package com.eshop.backend.product.dao;

import com.eshop.backend.product.dao.models.FilterModel;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
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
        String sql = ProductMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new ProductMapper(), new Object[]{Long.valueOf(id)});
    }

    @Override
    public List<ProductModel> getByName(String name) {
        Object[] params = new Object[] {"%" + name + "%"};
        StringBuilder sql = new StringBuilder(ProductMapper.SELECT_SQL);
        sql.append(" WHERE p.productname ILIKE ? and genre in (?)");
        return template.query(sql.toString(), new ProductMapper(), params);
    }

    @Override
    public List<ProductModel> getFiltered(int page, int size, FilterModel filterModel) {
        return null;
    }

    @Override
    public List<ProductModel> getAll() {
        return null;
    }

    @Override
    public List<ProductModel> getProductPage(int page, int size) {
        String sql = ProductMapper.SELECT_SQL +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return template.query(sql, new ProductMapper());
    }

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
        try {
            template.update(SQL, productModel.getProductName(), productModel.getProductAmount(),
                    productModel.getProductPrice(), productModel.getProductDiscount(),
                    productModel.getProductDate(), productModel.getProductPict(),
                    productModel.getProductDescription(), productModel.getProductStatus(),
                    productModel.getGenre(), productModel.getCoverType(),
                    productModel.getAuthor(), productModel.getLanguage(),
                    productModel.getPublisher(), productModel.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
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

    //delete later
    @Override
    public List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
        String inSql = String.join(",", Collections.nCopies(filter.size(), "?"));
        String sql = String.format(ProductMapper.SELECT_SQL +
                " left join productcategory on p.productcategory = productcategory.id" +
                " where productcategory.productcategoryname in (%s) order by p." + orderBy +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY", inSql);
        return template.query(sql, new ProductMapper(), filter.toArray());
    }

    @Override
    public List<ProductModel> getAllOrderBy(int page, int size, String orderBy) {
        String sql = ProductMapper.SELECT_SQL + " order by p." + orderBy +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return template.query(sql, new ProductMapper());
    }

    @Override
    public Integer getNumberOfSearchedOrderedFiltered(String search, String orderBy, FilterModel filterModel) {
        StringBuilder sql = new StringBuilder("SELECT COUNT (*) from product p  ");
        Object[] paramsForQuery = getSearchedOrderedFilteredBuilder(search, filterModel, sql);
        return template.queryForObject(sql.toString(), Integer.class, paramsForQuery);
    }

    @Override
    public void updateAmountById(Long id, int price) {
        String SQL = "update product set productamount = ? where id = ?";
        try {
            template.update(SQL, price, id);
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public Integer getAmountById(Long id) {
        try {
            String sql = "SELECT productamount FROM product where id = ?";
            return template.queryForObject(sql, Integer.class, id);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public List<ProductModel> getSearchedOrderedFiltered(int page, int size, String search, String orderBy, FilterModel filterModel) {
        StringBuilder sql = new StringBuilder(ProductMapper.SELECT_SQL); //select ... from product p
        Object[] paramsForQuery = getSearchedOrderedFilteredBuilder(search, filterModel, sql);
        if (!orderBy.equals("")) { //упорядочение по ...
            sql.append(" order by " + orderBy);
        }

        sql.append(" OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY");
        return template.query(sql.toString(), new ProductMapper(), paramsForQuery);
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
        String sql = "SELECT ";

//        String sql = ProductMapper.SELECT_SQL + " order by p." + orderBy +
//                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
//        return template.query(sql, new ProductMapper());
        return null;
    }

    @Override
    public List<ProductModel> getNew(int page, int size) {
        String sql = ProductMapper.SELECT_SQL + " order by p.productdate desc " +
                " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        return template.query(sql, new ProductMapper(), new Object[]{(page - 1), size});
    }

    private String getAuthorById(int id){
        String sql = "SELECT authorname from author where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private String getCoverTypeById(int id){
        String sql = "SELECT covertypename from covertype where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private String getGenreById(int id){
        String sql = "SELECT genrename from genre where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private String getLanguageById(int id){
        String sql = "SELECT languagename from language where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private String getPublisherById(int id){
        String sql = "SELECT publishername from publisher where id = ?";
        return template.queryForObject(sql, String.class, new Object[]{Long.valueOf(id)});
    }

    private Object[] getSearchedOrderedFilteredBuilder(String search, FilterModel filterModel, StringBuilder sql){
        Object[] paramsForQuery;
        String paramLike = "";
        ArrayList<Long> paramsFilter = new ArrayList<>();
        String paramOrderBy = "";
        int paramsForQueryLength = 0;
        int paramsForQueryIterator = 0;


        if (!search.equals("")) {
            sql.append(" WHERE p.productname ILIKE ? "); //тут поиск по имени продукта
            paramLike="%" + search + "%";
            paramsForQueryLength++;
        }
        if ((filterModel.getAuthor().length != 0) ||
                (filterModel.getGenre().length != 0) ||
                (filterModel.getLanguage().length != 0) ||
                (filterModel.getPublisher().length != 0) ||
                (filterModel.getCoverType().length != 0)) { //проверка на то, что хотя бы один из фильтров выбран
            if (!search.equals("")) { //если первый if сработал, тогда надо добавить AND
                sql.append(" AND ");
            } else {
                sql.append(" WHERE ");
            }

            sql.append(filterSqlBuilder2(filterModel, paramsFilter)); //Тут собираю часть с выбранными фильтрами, (под номером 2 он временно)
            paramsForQueryLength+=paramsFilter.size();
        }


        paramsForQuery = new Object[paramsForQueryLength];

        if (!paramLike.equals("")){
            paramsForQuery[paramsForQueryIterator] = paramLike;
            paramsForQueryIterator++;
        }
        if (paramsFilter.size()!=0){
            for (int i=0; i<paramsFilter.size(); i++){
                paramsForQuery[paramsForQueryIterator] = paramsFilter.get(i);
                paramsForQueryIterator++;
            }
            paramsForQueryLength+=paramsFilter.size();
        }

        return paramsForQuery;
    }

    private String filterSqlBuilder2(FilterModel filterModel, ArrayList<Long> params) {
        StringBuilder filters = new StringBuilder();
        filterInBuilder(filters, filterModel.getAuthor(), " p.author ", params);
        filterInBuilder(filters, filterModel.getCoverType(), " p.covertype ", params);
        filterInBuilder(filters, filterModel.getGenre(), " p.genre ", params);
        filterInBuilder(filters, filterModel.getLanguage(), " p.language ", params);
        filterInBuilder(filters, filterModel.getPublisher(), " p.publisher ", params);
        return filters.substring(0, filters.length() - 4); //удаляет лишний AND
    }


    private void filterInBuilder(StringBuilder stringBuilder, Long[] foreignKeys, String fkName, ArrayList<Long> params) {
        if (foreignKeys.length != 0) {
            stringBuilder.append(fkName);
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
