package top.chr0nix.maa4j.entity.converter;

import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import top.chr0nix.maa4j.entity.AccountEntity;

import java.util.List;

public class JsonConverter implements AttributeConverter<List<AccountEntity>, String> {
    @Override
    public String convertToDatabaseColumn(List<AccountEntity> accountEntities) {
        Gson gson = new Gson();
        return gson.toJson(accountEntities);
    }

    @Override
    public List<AccountEntity> convertToEntityAttribute(String s) {
        Gson gson = new Gson();
        return gson.fromJson(s, List.class);
    }
}
