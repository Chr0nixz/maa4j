package top.chr0nix.maa4j.maa.MaaTasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FightTask extends MaaTask{

    String stage;

    @Builder.Default
    int medicine = 0;

}
