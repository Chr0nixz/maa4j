package top.chr0nix.maa4j.maa.MaaTasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitTask extends MaaTask{

    @Builder.Default
    boolean refresh = true;

    @Builder.Default
    ArrayList<Integer> select = new ArrayList<>(Arrays.asList(4, 5, 6));

    @Builder.Default
    ArrayList<Integer> confirm = new ArrayList<>(Arrays.asList(4, 5, 6));

    @Builder.Default
    int times = 4;

    @Builder.Default
    boolean skip_robot = false;

}
