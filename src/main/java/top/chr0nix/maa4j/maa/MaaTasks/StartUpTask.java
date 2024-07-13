package top.chr0nix.maa4j.maa.MaaTasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartUpTask extends MaaTask{

    @Builder.Default
    String client_type = "Official";

    boolean start_game_enabled;

}
