package top.chr0nix.maa4j.entity.taskConfig;

import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;

import java.util.List;

public abstract class TaskConfig {

    public abstract boolean check();

    public abstract List<MaaTask> getTask();

}
