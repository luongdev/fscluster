package luongld.freeswitch.appconfig.converters;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NullAsEmptyStringConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return StringUtils.isNotEmpty(attribute) ? attribute : "";
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return StringUtils.isNotEmpty(dbData) ? dbData : "";
    }
}
