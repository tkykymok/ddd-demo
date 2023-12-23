package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.SeqNo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class SeqNoToInteger implements Converter<SeqNo, JdbcValue> {
    @Override
    public JdbcValue convert(SeqNo source) {
        return JdbcValue.of(source.value(), JDBCType.INTEGER);
    }
}
