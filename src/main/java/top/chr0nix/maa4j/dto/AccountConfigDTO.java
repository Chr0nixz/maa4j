package top.chr0nix.maa4j.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.chr0nix.maa4j.entity.taskConfig.AwardConfig;
import top.chr0nix.maa4j.entity.taskConfig.InfrastConfig;
import top.chr0nix.maa4j.entity.taskConfig.MallConfig;
import top.chr0nix.maa4j.entity.taskConfig.RecruitConfig;
import top.chr0nix.maa4j.entity.taskConfig.fight.FightConfig;

@Data
@Schema(description = "账号设置（均已内置默认值）")
public class AccountConfigDTO {

    @Schema(description = "开启作战")
    boolean enableFight;

    @Schema(description = "开启基建排班")
    boolean enableInfrast;

    @Schema(description = "开启公招")
    boolean enableRecruit;

    @Schema(description = "开启收取邮件")
    boolean enableMall;

    @Schema(description = "开启领取奖励")
    boolean enableAward;

    @Schema
    FightConfig fightConfig;

    @Schema
    InfrastConfig infrastConfig;

    @Schema
    RecruitConfig recruitConfig;

    @Schema
    MallConfig mallConfig;

    @Schema
    AwardConfig awardConfig;

}
