package top.chr0nix.maa4j.entity.taskConfig;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;
import top.chr0nix.maa4j.maa.MaaTasks.MallTask;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "领取信用及商店购物设置")
public class MallConfig extends TaskConfig{

    @Schema(description = "是否购物")
    boolean shopping;

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public List<MaaTask> generateTask() {
        return Collections.singletonList(MallTask.builder().shopping(shopping).build());
    }
}
