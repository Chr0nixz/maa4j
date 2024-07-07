package top.chr0nix.maa4j.dto;

import lombok.Data;
import top.chr0nix.maa4j.entity.config.InfrastConfig;
import top.chr0nix.maa4j.entity.config.RecruitConfig;
import top.chr0nix.maa4j.entity.config.fight.FightConfig;

@Data
public class AccountConfigDTO {

    Long accountId;

    boolean enableFight;

    boolean enableInfrast;

    boolean enableRecruit;

    FightConfig fightConfig;

    InfrastConfig infrastConfig;

    RecruitConfig recruitConfig;

}
