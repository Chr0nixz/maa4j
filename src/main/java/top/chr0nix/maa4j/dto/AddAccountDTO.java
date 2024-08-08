package top.chr0nix.maa4j.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "添加账号")
public class AddAccountDTO {

    @Schema(description = "账号")
    private String account;

    @Schema(description = "密码")
    private String password;

}
