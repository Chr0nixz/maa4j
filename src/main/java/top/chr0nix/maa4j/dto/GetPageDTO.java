package top.chr0nix.maa4j.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;

@Data
@Schema(description = "获得分页")
public class GetPageDTO {

    @Schema(description = "页序号")
    int pageNum;

    @Schema(description = "页大小")
    int size;

    @Schema(description = "排序方式（0正序，1逆序）")
    HashMap<String, Integer> orders;

}
