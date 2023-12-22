package com.example.demo.infrastructure.queryservice.user;

import com.example.demo.application.usecase.user.UserOrderProduct;
import com.example.demo.domain.queryservice.user.UserOrderProductQueryService;
import com.example.demo.infrastructure.support.SQLLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserOrderProductQueryServiceImpl implements UserOrderProductQueryService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SQLLoader sqlLoader;

    public UserOrderProductQueryServiceImpl(NamedParameterJdbcTemplate jdbcTemplate, SQLLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    @Override
    public List<UserOrderProduct> findUserOrderProductDetails() throws IOException {
        String sql = sqlLoader.loadSQL("findUserOrderProductDetails.sql");

        RowMapper<UserOrderProduct> rowMapper = (rs, rowNum) -> new UserOrderProduct(
                rs.getLong(UserOrderProduct.COLUMN_USER_ID),
                rs.getString(UserOrderProduct.COLUMN_USER_NAME),
                rs.getString(UserOrderProduct.COLUMN_USER_EMAIL),
                rs.getLong(UserOrderProduct.COLUMN_ORDER_ID),
                rs.getString(UserOrderProduct.COLUMN_ORDER_DATE),
                rs.getLong(UserOrderProduct.COLUMN_PRODUCT_ID),
                rs.getString(UserOrderProduct.COLUMN_PRODUCT_NAME),
                rs.getDouble(UserOrderProduct.COLUMN_PRODUCT_PRICE)
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}