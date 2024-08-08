package top.chr0nix.maa4j.entity.taskConfig;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "公开招募设置")
public class RecruitConfig extends TaskConfig{

    @Schema(description = "招募多少次")
    int times;

    @Schema(description = "是否在识别到小车词条时跳过")
    boolean skipRobot;

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public List<MaaTask> generateTask() {
        return Collections.singletonList(RecruitTask.builder().times(times).skip_robot(skipRobot).build());
    }

}
