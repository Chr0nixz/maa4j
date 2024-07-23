package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.maa.MaaCallback;
import top.chr0nix.maa4j.maa.MaaInstance;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.service.intf.DeviceService;
import top.chr0nix.maa4j.service.intf.MaaService;
import top.chr0nix.maa4j.utils.DynamicInfo;
import top.chr0nix.maa4j.utils.model.AccountTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MaaServiceImpl implements MaaService {

    @Value("${maa4j.adb_path}")
    String adbPath;

    private final MaaCallback callback = new MaaCallback(this);

    private final DeviceService deviceService;
    private final DynamicInfo dynamicInfo;
    private final AccountService accountService;

    private final ConcurrentHashMap<String, MaaInstance> instancePool = new ConcurrentHashMap<>();

    @Autowired
    public MaaServiceImpl(DeviceService deviceService,
                          DynamicInfo dynamicInfo,
                          AccountService accountService) {
        this.deviceService = deviceService;
        this.dynamicInfo = dynamicInfo;
        this.accountService = accountService;
    }

    @Override
    public boolean startTask(AccountTask accountTask) {
        String account = accountTask.getAccount();
        String device = deviceService.applyDevice();
        if (Objects.equals(device, "")) {
            return false;
        }
        if (createInstance(account, device, account)) {
            appendTasks(account, accountTask.getTasks());
        }
        startInstance(account);
        accountService.gameLogin(account);
        return true;
    }

    @Override
    public boolean createInstance(String account, String host, String config){
        MaaInstance maaInstance = new MaaInstance(account, adbPath, host, config, callback);
        if (maaInstance.connect()) {
            instancePool.put(account, maaInstance);
        }
        return true;
    }

    @Override
    public ArrayList<Integer> appendTasks(String account, Collection<MaaTask> tasks) {
        ArrayList<Integer> list = new ArrayList<>();
        MaaInstance instance = instancePool.get(account);
        for (MaaTask task : tasks) {
            list.add(instance.appendTask(task));
        }
        return list;
    }

    @Override
    public int appendTask(String account, MaaTask task){
        return instancePool.get(account).appendTask(task);
    }

    @Override
    public void startInstance(String account){
        instancePool.get(account).start();
    }

    @Override
    public void destroyInstance(String account) {
        deviceService.releaseDevice(instancePool.get(account).getHost());
        instancePool.remove(account);
        dynamicInfo.getWorkAccountList().remove(account);
    }

}
