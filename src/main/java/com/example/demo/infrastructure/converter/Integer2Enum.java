package com.example.demo.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class Integer2Enum<E extends Enum> implements Converter<Integer, E> {
    private final Class<E> type;
    private final E[] enums;

    public Integer2Enum(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public E convert(Integer i) {
        return toOrdinalEnum(i);
    }

    private E toOrdinalEnum(int ordinal) {
        try {
            return enums[ordinal];
        } catch (Exception ex) {
            return null;
        }
    }
}