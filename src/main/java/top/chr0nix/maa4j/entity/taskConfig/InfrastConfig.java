package top.chr0nix.maa4j.entity.taskConfig;

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
public class InfrastConfig extends TaskConfig {

    ArrayList<String> facility;

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
    public List<MaaTask> getTask(){
        return Collections.singletonList(InfrastTask.builder().facility(facility).drones(drones).build());
    }

}
