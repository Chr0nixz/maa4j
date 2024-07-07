package top.chr0nix.maa4j.entity.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LongListConverter implements AttributeConverter<ArrayList<Long>, String> {

    @Override
    public String convertToDatabaseColumn(ArrayList<Long> stringList) {
        Gson gson = new Gson();
        return gson.toJson(stringList);
    }

    @Override
    public ArrayList<Long> convertToEntityAttribute(String s) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Long>>(){}.getType();
        return gson.fromJson(s, type);
    }

}
