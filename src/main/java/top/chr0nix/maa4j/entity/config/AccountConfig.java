package top.chr0nix.maa4j.entity.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.entity.config.fight.FightConfig;
import top.chr0nix.maa4j.exception.config.WrongFightConfigException;
import top.chr0nix.maa4j.exception.config.WrongInfrastConfigException;
import top.chr0nix.maa4j.exception.config.WrongRecruitConfigException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountConfig {

    boolean enableFight;

    boolean enableInfrast;

    boolean enableRecruit;

    FightConfig fightConfig;

    InfrastConfig infrastConfig;

    RecruitConfig recruitConfig;

    public void loadDTO(AccountConfigDTO accountConfigDTO) {

        enableFight = accountConfigDTO.isEnableFight();
        enableInfrast = accountConfigDTO.isEnableInfrast();
        enableRecruit = accountConfigDTO.isEnableRecruit();
        fightConfig = accountConfigDTO.getFightConfig();
        infrastConfig = accountConfigDTO.getInfrastConfig();
        recruitConfig = accountConfigDTO.getRecruitConfig();
        if (enableFight) {
            if (fightConfig == null || !fightConfig.check()) {
                throw new WrongFightConfigException();
            }
        }
        if (enableInfrast) {
            if (infrastConfig == null || !infrastConfig.check()) {
                throw new WrongFightConfigException();
            }
        }
        if (enableRecruit) {
            if (recruitConfig == null || !recruitConfig.check()) {
                throw new WrongFightConfigException();
            }
        }
    }

}
