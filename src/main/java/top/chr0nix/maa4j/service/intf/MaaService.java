package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;
import top.chr0nix.maa4j.utils.model.AccountTask;

import java.util.ArrayList;
import java.util.Collection;

public interface MaaService {

    boolean startTask(AccountTask accountTask);

    boolean createInstance(String account, String host, String config);

    ArrayList<Integer> appendTasks(String account, Collection<MaaTask> tasks);

    int appendTask(String account, MaaTask task);

    void startInstance(String account);

    void destroyInstance(String account);

}
