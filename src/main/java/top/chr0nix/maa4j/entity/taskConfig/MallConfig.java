package top.chr0nix.maa4j.entity.taskConfig;

import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;
import top.chr0nix.maa4j.maa.MaaTasks.MallTask;

import java.util.Collections;
import java.util.List;

public class MallConfig extends TaskConfig{

    boolean shopping;

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public List<MaaTask> getTask() {
        return Collections.singletonList(MallTask.builder().shopping(shopping).build());
    }
}
