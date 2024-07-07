package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.maa.MaaTasks.AbstractTask;

public interface MaaService {

    boolean createInstance(String account, String host, String config);

    int appendTask(String account, AbstractTask task);

    void start(String account);

}
