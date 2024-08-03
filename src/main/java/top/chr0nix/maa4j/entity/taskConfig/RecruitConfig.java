package top.chr0nix.maa4j.entity.taskConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;
import top.chr0nix.maa4j.maa.MaaTasks.RecruitTask;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitConfig extends TaskConfig{

    int times;

    boolean skipRobot;

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public List<MaaTask> getTask() {
        return Collections.singletonList(RecruitTask.builder().times(times).skip_robot(skipRobot).build());
    }

}
