package top.chr0nix.maa4j.entity.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;

import java.lang.reflect.Type;
import java.util.HashMap;

public class AuthorityHashMapConverter implements AttributeConverter<HashMap<String, Boolean>, String> {
    @Override
    public String convertToDatabaseColumn(HashMap<String, Boolean> stringBooleanHashMap) {
        Gson gson = new Gson();
        return gson.toJson(stringBooleanHashMap);
    }

    @Override
    public HashMap<String, Boolean> convertToEntityAttribute(String string) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Boolean>>(){}.getType();
        return gson.fromJson(string, type);
    }
}
