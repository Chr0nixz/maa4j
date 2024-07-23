package top.chr0nix.maa4j.entity.taskConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.entity.taskConfig.fight.FightConfig;
import top.chr0nix.maa4j.exception.config.WrongFightConfigException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountConfig {

    boolean enableFight;

    boolean enableInfrast;

    boolean enableRecruit;

    boolean enableMall;

    boolean enableAward;

    FightConfig fightConfig = new FightConfig();

    InfrastConfig infrastConfig = new InfrastConfig();

    RecruitConfig recruitConfig = new RecruitConfig();

    MallConfig mallConfig = new MallConfig();

    AwardConfig awardConfig = new AwardConfig();

    public void loadDTO(AccountConfigDTO accountConfigDTO) {
        enableFight = accountConfigDTO.isEnableFight();
        enableInfrast = accountConfigDTO.isEnableInfrast();
        enableRecruit = accountConfigDTO.isEnableRecruit();
        enableMall = accountConfigDTO.isEnableMall();
        enableAward = accountConfigDTO.isEnableAward();
        fightConfig = accountConfigDTO.getFightConfig();
        infrastConfig = accountConfigDTO.getInfrastConfig();
        recruitConfig = accountConfigDTO.getRecruitConfig();
        mallConfig = accountConfigDTO.getMallConfig();
        awardConfig = accountConfigDTO.getAwardConfig();
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
        if (enableMall) {
            if (mallConfig == null || !mallConfig.check()) {
                throw new WrongFightConfigException();
            }
        }
        if (enableAward) {
            if (awardConfig == null || !awardConfig.check()) {
                throw new WrongFightConfigException();
            }
        }
    }

}
