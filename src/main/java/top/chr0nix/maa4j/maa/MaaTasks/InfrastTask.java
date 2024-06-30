package top.chr0nix.maa4j.maa.MaaTasks;

import java.util.List;

public class InfrastTask extends AbstractTask{

    public InfrastTask(
            List<String> facility,
            String drones
    ) {
        String paramsModel = """
                {
                "facility": %s,
                "drones": "%s",
                }
                """;
        this.type = "Infrast";
        this.params = String.format(paramsModel, facility, drones);
    }

}
