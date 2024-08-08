package top.chr0nix.maa4j.entity.taskConfig;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.maa.MaaTasks.AwardTask;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "领取各种奖励设置")
public class AwardConfig extends TaskConfig {

    @Schema(description = "领取每日/每周任务奖励")
    boolean award;

    @Schema(description = "领取所有邮件奖励", example = "false")
    boolean mail;

    @Schema(description = "领取限定池子赠送的每日免费单抽", example = "false")
    boolean recruit;

    @Schema(description = "领取幸运墙的合成玉奖励", example = "false")
    boolean orundum;

    @Schema(description = "领取限时开采许可的合成玉奖励", example = "false")
    boolean mining;

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public List<MaaTask> generateTask() {
        return Collections.singletonList(AwardTask.builder().award(award).mail(mail).recruit(recruit)
                .orundum(orundum).mining(mining).build());
    }
}
