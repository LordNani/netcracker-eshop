package com.eshop.backend.product.dao;

import com.eshop.backend.product.dao.models.FilterModel;
import com.eshop.backend.product.dao.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
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
                "                     productdate, productdescription,\n" +
                "                     productstatus, genre, " +
                "                     covertype, author," +
                "                     language, publisher)\n" +
                "                     values (?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(SQL, productModel.getProductName(), productModel.getProductAmount(),
                productModel.getProductPrice(), productModel.getProductDiscount(),
                productModel.getProductDate(), productModel.getProductDescription(),
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
//        try {
//            String sql = ProductMapper.SELECT_SQL +
//                    " WHERE p.productname ILIKE ?";
//            PreparedStatementCreator statementCreator = con -> {
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + name + "%");
//                return ps;
//            };
//            return template.query(statementCreator, new ProductMapper());
//        } catch (Exception e) {
//            String str = e.toString();
//        }
//        return null;
        //----------------------
        Object[] params = new Object[2];
        params[0] = "%" + name + "%";
        params[1] = 1;
        StringBuilder sql = new StringBuilder(ProductMapper.SELECT_SQL);
        sql.append(" WHERE p.productname ILIKE ? and genre in (?)");
//        params.add(new Object[] {"%" + name + "%"});
        //-----------------------
        return template.query(sql.toString(), new ProductMapper(), params);


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

    public void remove(Long id) {
//        String SQL = "update product set \"productStatus\" = false where id = ?";
//        template.update(SQL, id);
    }

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
    public List<ProductModel> getFiltered(int page, int size, FilterModel filterModel) {
        String sql = ProductMapper.SELECT_SQL +
                filterSqlBuilder(filterModel) + " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return template.query(sql, new ProductMapper());
    }

    @Override
    public Integer getNumberOfSearchedOrderedFiltered(String search, String orderBy, FilterModel filterModel) {
        StringBuilder sql = new StringBuilder("SELECT COUNT (*) from product p  ");
        Object[] paramsForQuery = getSearchedOrderedFilteredBuilder(search, filterModel, sql);
        return template.queryForObject(sql.toString(), Integer.class, paramsForQuery);
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

    private String filterSqlBuilder(FilterModel filterModel) {
        if ((filterModel.getAuthor().length == 0) &&
                (filterModel.getGenre().length == 0) &&
                (filterModel.getLanguage().length == 0) &&
                (filterModel.getPublisher().length == 0) &&
                (filterModel.getCoverType().length == 0)) {
            return "";
        }
        StringBuilder filters = new StringBuilder();
        filters.append(" WHERE ");
//        filterInBuilder(filters, filterModel.getAuthor(), " p.author ");
//        filterInBuilder(filters, filterModel.getCoverType(), " p.covertype ");
//        filterInBuilder(filters, filterModel.getGenre(), " p.genre ");
//        filterInBuilder(filters, filterModel.getLanguage(), " p.language ");
//        filterInBuilder(filters, filterModel.getPublisher(), " p.publisher ");
        return filters.substring(0, filters.length() - 4);
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

    private Long getProductsCount(){
        return null;
    }
}
