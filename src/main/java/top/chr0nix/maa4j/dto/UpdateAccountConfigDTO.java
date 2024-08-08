package top.chr0nix.maa4j.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class UpdateAccountConfigDTO {

    @Schema
    String property;

    @Schema
    String value;

}
