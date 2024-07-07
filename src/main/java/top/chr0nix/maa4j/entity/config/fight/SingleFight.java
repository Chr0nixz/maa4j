package top.chr0nix.maa4j.entity.config.fight;

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
