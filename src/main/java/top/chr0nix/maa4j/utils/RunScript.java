package top.chr0nix.maa4j.utils;

import com.google.gson.Gson;
import com.sun.jna.Native;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.chr0nix.maa4j.maa.MaaCore;
import top.chr0nix.maa4j.utils.model.Maa4jProperties;
import top.chr0nix.maa4j.utils.model.MemoryInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.UUID;

import static top.chr0nix.maa4j.utils.JWTUtils.SECRET;
import static top.chr0nix.maa4j.maa.MaaInstance.maaCore;

@Component
public class RunScript implements ApplicationRunner {

    private Maa4jProperties maa4jProperties;
    private DynamicInfo dynamicInfo;

    @Autowired
    public void setMaa4jProperties(Maa4jProperties maa4jProperties){
        this.maa4jProperties = maa4jProperties;
    }

    @Autowired
    public void setDynamicInfo(DynamicInfo dynamicInfo) {
        this.dynamicInfo = dynamicInfo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("maa4j初始化...");

        String secret = maa4jProperties.getSecret();
        String maaPath = maa4jProperties.getMaa_path();
        List<String> devices = maa4jProperties.getDevices();

        File file = new File(System.getProperty("user.dir") + File.separator + "config" + File.separator + "data.json");
        if (file.exists()) {
            Gson gson = new Gson();
            dynamicInfo.load(gson.fromJson(new BufferedReader(new FileReader(file)), MemoryInfo.class));
        } else {
            if (!devices.isEmpty()) {
                for (String device : devices) {

                }
            }
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
}
