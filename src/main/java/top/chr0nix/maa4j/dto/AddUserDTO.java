package top.chr0nix.maa4j.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "注册用户")
public class AddUserDTO {

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "密码")
    private String password;

}
