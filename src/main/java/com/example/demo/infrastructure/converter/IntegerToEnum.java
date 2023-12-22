package com.example.demo.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class IntegerToEnum<E extends Enum<E>> implements Converter<Integer, E> {
    private final E[] enums;

    public IntegerToEnum(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public E convert(Integer i) {
        return convertToEnum(i);
    }

    private E convertToEnum(int ordinal) {
        try {
            return enums[ordinal];
        } catch (Exception ex) {
            return null;
        }
    }
}