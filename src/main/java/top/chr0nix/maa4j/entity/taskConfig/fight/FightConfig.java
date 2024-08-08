package top.chr0nix.maa4j.entity.taskConfig.fight;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.entity.taskConfig.TaskConfig;
import top.chr0nix.maa4j.maa.MaaTasks.FightTask;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "刷理智设置")
public class FightConfig extends TaskConfig {

    @Schema(description = "作战列表")
    List<SingleFight> fightList;

    @Schema(description = "最大使用理智药数量")
    int medicine;

    @Override
    public boolean check() {
        for (SingleFight fight : fightList) {
            if (!fight.check()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<MaaTask> generateTask() {
        ArrayList<MaaTask> list = new ArrayList<>();
        for (SingleFight fight : fightList) {
            list.add(FightTask.builder().stage(fight.getStage()).build());
        }
        return list;
    }

}
