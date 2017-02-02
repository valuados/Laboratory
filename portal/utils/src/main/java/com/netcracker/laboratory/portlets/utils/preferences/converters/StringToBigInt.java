package com.netcracker.laboratory.portlets.utils.preferences.converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.math.BigInteger;

public class StringToBigInt implements Converter<String, BigInteger> {
    @Override
    public BigInteger convert(String source) {
        BigInteger result = null;
        if (StringUtils.isNotBlank(source) && StringUtils.isNumeric(source)) {
            result = new BigInteger(source);
        }
        return result;
    }
}
