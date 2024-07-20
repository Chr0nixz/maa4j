package top.chr0nix.maa4j.dto;

import lombok.Data;
import top.chr0nix.maa4j.entity.taskConfig.InfrastConfig;
import top.chr0nix.maa4j.entity.taskConfig.RecruitConfig;
import top.chr0nix.maa4j.entity.taskConfig.fight.FightConfig;

@Data
public class AccountConfigDTO {

    Long accountId;

    boolean enableFight;

    boolean enableInfrast;

    boolean enableRecruit;

    FightConfig fightConfig = new FightConfig();

    InfrastConfig infrastConfig = new InfrastConfig();

    RecruitConfig recruitConfig = new RecruitConfig();

}
