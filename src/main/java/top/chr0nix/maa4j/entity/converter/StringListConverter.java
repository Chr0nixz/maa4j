package top.chr0nix.maa4j.entity.converter;

import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;

import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        Gson gson = new Gson();
        return gson.toJson(stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        Gson gson = new Gson();
        return gson.fromJson(s, List.class);
    }
}
