package com.progastination.service.impl;

import com.progastination.dto.ProductDto;
import com.progastination.dto.ProductFilterDto;
import com.progastination.service.ProductFilterService;
import com.progastination.utils.select.SelectBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
// todo add title or producer after improving select builder
/**
* 23.01.21
* @author Elya
*
* service that filters data without iterating over different combinations of nonnull filter parameters
* @see com.progastination.utils.select.SelectBuilder
* */

@Component
@AllArgsConstructor
public class ProductFilterServiceImpl implements ProductFilterService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductDto> filterProducts(ProductFilterDto filter, int page, int pageSize) {
        SelectBuilder selectBuilder = new SelectBuilder();
        String select = selectBuilder
                .table(ProductTableConstant.TABLE_NAME)
                .where(ProductTableConstant.WEIGHT, filter.getWeights(), SelectBuilder.SelectCondition.IN)
                .where(ProductTableConstant.CATEGORY_ID, filter.getCategoryIdentifiers(), SelectBuilder.SelectCondition.IN)
                .where(ProductTableConstant.TITLE, filter.getSearch(), SelectBuilder.SelectCondition.LIKE)
                .limit(pageSize)
                .offset(page * pageSize)
                .condition(SelectBuilder.JoinCondition.AND)
                .build();

        return jdbcTemplate.query(select, rowMapper());
    }

    // todo set fields (i'm too lazy)
    private RowMapper<ProductDto> rowMapper() {
        return (rs, i) -> {
            ProductDto product = new ProductDto();
            product.setEan(rs.getString(rs.findColumn(ProductTableConstant.EAN)));
            product.setWeight(rs.getInt(rs.findColumn(ProductTableConstant.WEIGHT)));
            product.setTitle(rs.getString(rs.findColumn(ProductTableConstant.TITLE)));
            product.setCategoryId(rs.getString(rs.findColumn(ProductTableConstant.CATEGORY_ID)));
//            product.setShop();
//            product.setUnit();
            product.setWebUrl(rs.getString(rs.findColumn(ProductTableConstant.WEB_URL)));
            product.setPrice(rs.getInt(rs.findColumn(ProductTableConstant.PRICE)));
            return product;
        };
    }

    @Data
    static class ProductTableConstant {
        public final static String TABLE_NAME = "products";
        public final static String EAN = "ean";
        public final static String WEIGHT = "weight";
        public final static String CATEGORY_ID = "category_id";
        public final static String IMAGE = "image";
        public final static String PRICE = "price";
        public final static String SHOP = "shop";
        public final static String TITLE = "title";
        public final static String WEB_URL = "web_url";
        public final static String PARENT_CATEGORY_ID  = "category_identifier";
    }
}
