package top.chr0nix.maa4j.entity.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(s, type);
    }
}
