package top.chr0nix.maa4j.entity.config;

import java.util.ArrayList;
import java.util.Arrays;

public class InfrastConfig {

    ArrayList<String> facility;

    String drones;

    public boolean check() {
        if (facility == null) {
            ArrayList<String> facility = new ArrayList<>(Arrays.asList(
                    "Mfg",
                    "Trade",
                    "Power",
                    "Control",
                    "Reception",
                    "Office",
                    "Dorm"));
        }
        if (drones == null) {
            drones = "_NotUse";
        }
        return true;
    }

}
