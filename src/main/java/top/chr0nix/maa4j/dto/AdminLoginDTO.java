package top.chr0nix.maa4j.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理员登录")
public class AdminLoginDTO {

    @Schema(description = "管理员用户名")
    String name;

    @Schema(description = "密码")
    String password;

}
