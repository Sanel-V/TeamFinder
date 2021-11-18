package hu.elte.teamfinder.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String>
{

    public final String DELIMITER;

    public StringSetConverter()
    {
        this.DELIMITER = ";";
    }

    public StringSetConverter(String delimiter)
    {
        this.DELIMITER = delimiter;
    }

    @Override
    public String convertToDatabaseColumn(Set<String> strings)
    {
        if(strings == null)
        {
            return "";
        }
        return String.join(DELIMITER, strings);
    }

    @Override
    public Set<String> convertToEntityAttribute(String stringColumn)
    {
        Set<String> result = new HashSet<>();
        StringTokenizer st = new StringTokenizer(stringColumn, DELIMITER);
        while (st.hasMoreTokens())
        {
            result.add(st.nextToken());
        }
        return result;

    }
}

