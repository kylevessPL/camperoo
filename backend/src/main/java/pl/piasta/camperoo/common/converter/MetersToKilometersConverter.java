package pl.piasta.camperoo.common.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

public class MetersToKilometersConverter extends StdConverter<Integer, Integer> {
    @Override
    public Integer convert(Integer meters) {
        return (int) Math.ceil((double) meters / 1000);
    }
}
