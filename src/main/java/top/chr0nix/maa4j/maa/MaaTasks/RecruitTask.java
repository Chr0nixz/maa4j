package top.chr0nix.maa4j.maa.MaaTasks;

import java.util.List;

public class RecruitTask extends AbstractTask{

    public RecruitTask(
            boolean refresh,
            List<Integer> select,
            List<Integer> confirm,
            int times,
            boolean skipRobot
    ) {
        String paramsModel = """
                {
                    "refresh": "%s",
                    "select": %s,
                    "confirm": %s,
                    "times": "%s",
                    "skip_robot": "%s",
                }
                """;
        this.type = "Recruit";
        this.params = String.format(paramsModel, refresh, select, confirm, times, skipRobot);
    }

}
