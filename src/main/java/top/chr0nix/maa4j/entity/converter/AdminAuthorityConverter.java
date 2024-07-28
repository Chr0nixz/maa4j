package top.chr0nix.maa4j.entity.converter;

import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import top.chr0nix.maa4j.utils.model.AdminAuthority;

public class AdminAuthorityConverter implements AttributeConverter<AdminAuthority, String> {

    @Override
    public String convertToDatabaseColumn(AdminAuthority adminAuthority) {
        Gson gson = new Gson();
        return gson.toJson(adminAuthority);
    }

    @Override
    public AdminAuthority convertToEntityAttribute(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, AdminAuthority.class);
    }

}
