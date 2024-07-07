package top.chr0nix.maa4j.entity.converter;

import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import top.chr0nix.maa4j.entity.config.AccountConfig;

public class AccountConfigConverter implements AttributeConverter<AccountConfig, String> {

    @Override
    public String convertToDatabaseColumn(AccountConfig accountConfig) {
        Gson gson = new Gson();
        return gson.toJson(accountConfig);
    }

    @Override
    public AccountConfig convertToEntityAttribute(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, AccountConfig.class);
    }

}
