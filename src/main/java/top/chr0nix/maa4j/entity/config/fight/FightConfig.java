package top.chr0nix.maa4j.entity.config.fight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
