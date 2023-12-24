package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.BaseId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;
import java.util.Objects;

@WritingConverter
public class AggregateRefIdToBigInt<T, ID extends BaseId<Long>> implements Converter<AggregateReference<T, ID>, JdbcValue> {
    @Override
    public JdbcValue convert(AggregateReference source) {
        return JdbcValue.of(((BaseId<?>) Objects.requireNonNull(source.getId())).value(), JDBCType.BIGINT);
    }
}