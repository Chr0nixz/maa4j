package top.chr0nix.maa4j.maa.MaaTasks;

public class StartUpTask extends AbstractTask{

    public StartUpTask(
            String clientType,
            boolean startGameEnabled,
            String accountName
                       ) {
        String paramsModel = """
                {
                    "client_type": "%s",
                    "start_game_enabled": "%s",
                    "account_name": "%s"
                }
                """;
        this.type = "StartUp";
        this.params = String.format(paramsModel, clientType, startGameEnabled, accountName);
    }

}
