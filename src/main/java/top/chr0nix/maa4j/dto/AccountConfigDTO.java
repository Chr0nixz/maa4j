package top.chr0nix.maa4j.dto;

import lombok.Data;
import top.chr0nix.maa4j.entity.taskConfig.AwardConfig;
import top.chr0nix.maa4j.entity.taskConfig.InfrastConfig;
import top.chr0nix.maa4j.entity.taskConfig.MallConfig;
import top.chr0nix.maa4j.entity.taskConfig.RecruitConfig;
import top.chr0nix.maa4j.entity.taskConfig.fight.FightConfig;

@Data
public class AccountConfigDTO {

    Long accountId;

    boolean enableFight;

    boolean enableInfrast;

    boolean enableRecruit;

    boolean enableMall;

    boolean enableAward;

    FightConfig fightConfig;

    InfrastConfig infrastConfig;

    RecruitConfig recruitConfig;

    MallConfig mallConfig;

    AwardConfig awardConfig;

}
