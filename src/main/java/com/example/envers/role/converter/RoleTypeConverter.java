package com.example.envers.role.converter;

import com.example.envers.role.entity.RoleType;
import org.springframework.core.convert.converter.Converter;

public class RoleTypeConverter implements Converter<String, RoleType> {

    @Override
    public RoleType convert(String source) {
        return RoleType.valueOf(source);
    }
}
