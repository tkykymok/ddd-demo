package com.example.demo.infrastructure.queryservice.order;

import com.example.demo.domain.queryservice.order.OrderDetailsResult;
import com.example.demo.domain.queryservice.order.OrderQueryService;
import com.example.demo.infrastructure.support.DatabaseConstants;
import com.example.demo.infrastructure.support.SQLLoader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SQLLoader sqlLoader;

    public OrderQueryServiceImpl(NamedParameterJdbcTemplate jdbcTemplate, SQLLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    // 注文IDに基づいて注文詳細を取得するメソッド
    @Override
    public List<OrderDetailsResult> findOrderDetailsById(Long orderId) throws IOException {
        // SQLファイルからSQL文を読み込む
        String sql = sqlLoader.loadSQL("findOrderDetailsById.sql");

        // パラメータを設定（ここでは注文ID）
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("orderId", orderId);

        // 結果セットからOrderDetailsResultオブジェクトを作成するRowMapperを定義
        RowMapper<OrderDetailsResult> rowMapper = (rs, rowNum) -> new OrderDetailsResult(
                rs.getLong(DatabaseConstants.ID_COLUMN),
                rs.getDate(DatabaseConstants.ORDER_DATE_COLUMN).toLocalDate(),
                rs.getBigDecimal(DatabaseConstants.TOTAL_AMOUNT_COLUMN),
                rs.getString(DatabaseConstants.PRODUCT_NAME_COLUMN),
                rs.getBigDecimal(DatabaseConstants.PRODUCT_PRICE_COLUMN),
                rs.getInt(DatabaseConstants.QUANTITY_COLUMN),
                rs.getBigDecimal(DatabaseConstants.SUB_TOTAL_AMOUNT_COLUMN)
        );

        // SQLを実行し、結果を返す
        return jdbcTemplate.query(sql, parameters, rowMapper);
    }
}
