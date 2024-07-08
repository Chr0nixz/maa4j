package top.chr0nix.maa4j.entity.config.fight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleFight {

    String stage;

    int times;

    public boolean check() {
        if (this.stage != null) {
            return true;
        }
        return false;
    }

}
