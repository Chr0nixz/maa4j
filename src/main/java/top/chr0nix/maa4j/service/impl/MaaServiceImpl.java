package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.maa.MaaCallback;
import top.chr0nix.maa4j.maa.MaaCore;
import top.chr0nix.maa4j.maa.MaaInstance;
import top.chr0nix.maa4j.maa.MaaTasks.AbstractTask;
import top.chr0nix.maa4j.service.intf.MaaService;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class MaaServiceImpl implements MaaService {

    @Value("${maa4j.adb-path}")
    String adbPath;

    MaaCore.AsstApiCallback callback = new MaaCallback();

    private ConcurrentHashMap<String, MaaInstance> instancePool = new ConcurrentHashMap();

    public boolean createInstance(String account, String host, String config){
        MaaInstance maaInstance = new MaaInstance(account, adbPath, host, config, callback);
        if (maaInstance.connect()) {
            instancePool.put(account, maaInstance);
        }
        return true;
    }

    public int appendTask(String account, AbstractTask task){
        return instancePool.get(account).appendTask(task.getType(), task.getParams());
    }

    public void start(String account){
        instancePool.get(account).start();
    }

}
