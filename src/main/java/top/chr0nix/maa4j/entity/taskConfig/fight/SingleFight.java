package top.chr0nix.maa4j.entity.taskConfig.fight;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "单个作战")
public class SingleFight {

    @Schema(description = "关卡名")
    String stage;

    @Schema(description = "次数")
    int times;

    public boolean check() {
        return this.stage != null;
    }

}
