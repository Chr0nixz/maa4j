package top.chr0nix.maa4j.entity.taskConfig;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.maa.MaaTasks.InfrastTask;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "基建换班设置")
public class InfrastConfig extends TaskConfig {

    @Schema(description = "要换班的设施（有序）")
    ArrayList<String> facility;

    @Schema(description = "无人机用途", example = "_NotUse")
    String drones;

    @Override
    public boolean check() {
        if (facility == null) {
            facility = new ArrayList<>(Arrays.asList(
                    "Mfg",
                    "Trade",
                    "Power",
                    "Control",
                    "Reception",
                    "Office",
                    "Dorm"));
        }
        if (drones == null) {
            drones = "_NotUse";
        }
        return true;
    }

    @Override
    public List<MaaTask> generateTask(){
        return Collections.singletonList(InfrastTask.builder().facility(facility).drones(drones).build());
    }

}
