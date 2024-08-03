package top.chr0nix.maa4j.utils;

import com.google.gson.Gson;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;

public class MaaTaskHandler {

    private static final Gson gson = new Gson();

    public static String getTaskType(MaaTask task) {
        return task.getClass().getSimpleName().replace("Task", "");
    }

    public static String getTaskParams(MaaTask task) {
        return gson.toJson(task);
    }

}
