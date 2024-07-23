package top.chr0nix.maa4j.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jna.Native;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.maa.AdbManager;
import top.chr0nix.maa4j.maa.MaaCore;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.service.intf.DeviceService;
import top.chr0nix.maa4j.service.intf.ScheduleService;
import top.chr0nix.maa4j.utils.model.Maa4jProperties;
import top.chr0nix.maa4j.utils.model.MemoryInfo;

import java.io.*;
import java.util.List;
import java.util.UUID;

import static top.chr0nix.maa4j.utils.JWTUtils.SECRET;
import static top.chr0nix.maa4j.maa.MaaInstance.maaCore;

@Component
public class RunScript implements ApplicationRunner {

    private final Maa4jProperties maa4jProperties;
    private final DynamicInfo dynamicInfo;
    private final AdbManager adbManager;
    private final AccountRepository accountRepo;
    private final ScheduleService scheduleService;
    private final DeviceService deviceService;

    private final String dataPath = System.getProperty("user.dir") + File.separator + "config" + File.separator + "data.json";

    @Autowired
    public RunScript(Maa4jProperties maa4jProperties,
                     DynamicInfo dynamicInfo,
                     AdbManager adbManager,
                     AccountRepository accountRepo,
                     ScheduleService scheduleService,
                     DeviceService deviceService) {
        this.maa4jProperties = maa4jProperties;
        this.dynamicInfo = dynamicInfo;
        this.adbManager = adbManager;
        this.accountRepo = accountRepo;
        this.scheduleService = scheduleService;
        this.deviceService = deviceService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("maa4j初始化...");

        //参数初始化
        String secret = maa4jProperties.getSecret();
        String maaPath = maa4jProperties.getMaa_path();
        boolean useData = maa4jProperties.isUse_data();

        //读取，初始化动态信息
        File file = new File(dataPath);
        if (file.exists() && useData) {
            Gson gson = new Gson();
            dynamicInfo.load(gson.fromJson(new BufferedReader(new FileReader(file)), MemoryInfo.class));
            updateDevice();
            deviceService.monitorDevice();
            scheduleService.queueInspect();
        } else {
            initDynamicInfo();
            new File(file.getParent()).mkdirs();
            file.createNewFile();
        }

        if (!secret.isEmpty()){
            SECRET = secret;
        } else {
            SECRET = UUID.randomUUID().toString().replace("-", "");
            System.out.println("随机秘钥:" + SECRET);
        }

        System.setProperty("jna.library.path", maaPath);
        MaaCore load = Native.load("MaaCore", MaaCore.class);
        load.AsstLoadResource(maaPath);
        maaCore = load;
        System.out.println("maa连接初始化完毕！");

        System.out.println("maa4j初始化完成！");
    }

    @PreDestroy
    public void destroy() throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String str = gson.toJson(dynamicInfo.dump());
        try {
            var printWriter = new PrintWriter(dataPath);
            printWriter.write(str);
            printWriter.close();
        } catch (FileNotFoundException e) {
            File file = new File(dataPath);
            new File(file.getParent()).mkdirs();
            file.createNewFile();
            var printWriter = new PrintWriter(dataPath);
            printWriter.write(str);
            printWriter.close();
        }
    }

    public void initDynamicInfo() {
        //设备初始化
        updateDevice();
        //添加运行账号
        List<AccountEntity> accounts = accountRepo.findAccountEntitiesByRunningEqualsOrderByPriority(true);
        for (AccountEntity account : accounts) {
            dynamicInfo.getWaitAccountQueue().add(account.getId());
            dynamicInfo.setAccountSanZero(account.getId());
        }
    }

    public void updateDevice() {
        List<String> devices = maa4jProperties.getDevices();
        if (!devices.isEmpty()) {
            for (String device : devices) {
                if (adbManager.connect(device)) {
                    dynamicInfo.getDeviceStatusMap().put(device, 1);
                }
            }
        }
    }


}
