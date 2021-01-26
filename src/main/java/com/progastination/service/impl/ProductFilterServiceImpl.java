package com.progastination.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progastination.dto.*;
import com.progastination.entity.Shop;
import com.progastination.service.ProductFilterService;
import com.progastination.utils.select.SelectBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
// todo add title or producer after improving select builder

/**
 * 23.01.21
 *
 * @author Elya
 * <p>
 * service that filters data without iterating over different combinations of nonnull filter parameters
 * @see com.progastination.utils.select.SelectBuilder
 */

@Slf4j
@Component
@AllArgsConstructor
public class ProductFilterServiceImpl implements ProductFilterService {
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper mapper;

    @Override
    public List<ProductDto> filterProducts(ProductFilterDto filter, int page, int pageSize) {
        SelectBuilder selectBuilder = new SelectBuilder();
        String select = selectBuilder
                .table(ProductTableConstant.TABLE_NAME)
                .where(ProductTableConstant.WEIGHT, filter.getWeights(), SelectBuilder.SelectCondition.IN)
                .where(ProductTableConstant.CATEGORY_ID, filter.getCategories(), SelectBuilder.SelectCondition.IN)
                .where(ProductTableConstant.TITLE, filter.getSearch(), SelectBuilder.SelectCondition.LIKE)
                .limit(pageSize)
                .offset(page * pageSize)
                .condition(SelectBuilder.JoinCondition.AND)
//                .sort(ProductTableConstant.TITLE, filter.getSortPrice())
                .build();

        return jdbcTemplate.query(select, rowMapper());
    }

    private RowMapper<ProductDto> rowMapper() {
        return (rs, i) -> {
            ProductDto product = new ProductDto();
            product.setEan(rs.getString(rs.findColumn(ProductTableConstant.EAN)));
            product.setWeight(rs.getInt(rs.findColumn(ProductTableConstant.WEIGHT)));
            product.setTitle(rs.getString(rs.findColumn(ProductTableConstant.TITLE)));
            product.setCategoryId(rs.getString(rs.findColumn(ProductTableConstant.CATEGORY_ID)));
            product.setImg(ImgDto.of(rs.getString(rs.findColumn(ProductTableConstant.IMAGE))));
            product.setProducer(mapProducer(((PGobject) rs.getObject(rs.findColumn(ProductTableConstant.PRODUCER))).getValue()));
            product.setWebUrl(rs.getString(rs.findColumn(ProductTableConstant.WEB_URL)));
            return product;
        };
    }

    private Map<Shop, Integer> mapPrice(String value) {
        try {
            Map<String, Integer> map = mapper.readValue(value, MapGenerified.class);
            if (nonNull(map)) {
                return map.entrySet().stream().collect(Collectors.toMap(o -> Shop.valueOf(o.getKey()), Map.Entry::getValue));
            }
            return Collections.emptyMap();
        } catch (JsonProcessingException e) {
            log.error("Error while converting jsonb to map ", e);
            return Collections.emptyMap();
        }
    }

    private ProducerDto mapProducer(String value) {
        try {
            return mapper.readValue(value, ProducerDto.class);
        } catch (JsonProcessingException e) {
            log.error("Error while converting jsonb to map ", e);
            return null;
        }
    }

    @Data
    static class ProductTableConstant {
        public final static String TABLE_NAME = "products";
        public final static String EAN = "ean";
        public final static String WEIGHT = "weight";
        public final static String CATEGORY_ID = "category_id";
        public final static String IMAGE = "image";
        public final static String PRICE = "prices";
        public final static String SHOP = "shop";
        public final static String TITLE = "title";
        public final static String WEB_URL = "web_url";
        public final static String PRODUCER = "producer";
        public final static String PARENT_CATEGORY_ID = "category_identifier";
    }
}
