package hu.elte.teamfinder.utils;

import hu.elte.teamfinder.security.AccountRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Converter
public class StringAccountRoleSetConverter implements AttributeConverter<Set<AccountRole>, String> {

    public final String DELIMITER;

    public StringAccountRoleSetConverter() {
        this.DELIMITER = ";";
    }

    public StringAccountRoleSetConverter(String delimiter) {
        this.DELIMITER = delimiter;
    }

    @Override
    public String convertToDatabaseColumn(Set<AccountRole> strings) {
        if (strings == null) {
            return "";
        }
        return String.join(
                DELIMITER,
                strings.stream()
                        .map(accountRole -> accountRole.name())
                        .collect(Collectors.toSet()));
    }

    @Override
    public Set<AccountRole> convertToEntityAttribute(String stringColumn) {
        Set<String> result = new HashSet<>();
        StringTokenizer st = new StringTokenizer(stringColumn, DELIMITER);
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result.stream().map(role -> AccountRole.valueOf(role)).collect(Collectors.toSet());
    }
}
