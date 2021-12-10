package hu.elte.teamfinder.utils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongListConverter implements AttributeConverter<List<Long>, String> {
    @Override
    public String convertToDatabaseColumn(List<Long> longs) {
        return longs != null ? String.join(";", longs.toString()) : "";
    }

    @Override
    public List<Long> convertToEntityAttribute(String s) {
        if (s != null) {
            List<String> stringList = Arrays.asList(s.split(";"));
            List<Long> longList = new ArrayList<>();
            for (String str : stringList) {
                longList.add(Long.parseLong(str));
            }
            return longList;
        } else {
            return Collections.emptyList();
        }
    }
}
