package top.chr0nix.maa4j.entity.config.fight;

import java.util.List;

public class FightConfig {

    List<SingleFight> fightList;

    int medicine;

    public boolean check() {
        for (SingleFight fight : fightList) {
            if (!fight.check()) {
                return false;
            }
        }
        return true;
    }

}
