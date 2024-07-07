package top.chr0nix.maa4j.entity.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.entity.config.fight.FightConfig;
import top.chr0nix.maa4j.exception.config.WrongFightConfigException;

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

        this.enableFight = accountConfigDTO.isEnableFight();

        this.enableInfrast = accountConfigDTO.isEnableInfrast();

        this.enableRecruit = accountConfigDTO.isEnableRecruit();

        if (this.enableFight && accountConfigDTO.getFightConfig() == null) {
            throw new WrongFightConfigException();
        }



    }

}
