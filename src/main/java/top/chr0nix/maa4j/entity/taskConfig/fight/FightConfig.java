package top.chr0nix.maa4j.entity.taskConfig.fight;

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
public class FightConfig extends TaskConfig {

    List<SingleFight> fightList;

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
    public List<MaaTask> getTask() {
        ArrayList<MaaTask> list = new ArrayList<>();
        for (SingleFight fight : fightList) {
            list.add(FightTask.builder().stage(fight.getStage()).build());
        }
        return list;
    }

}
