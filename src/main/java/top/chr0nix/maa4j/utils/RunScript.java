package top.chr0nix.maa4j.utils;

import com.sun.jna.Native;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.chr0nix.maa4j.maa.MaaCore;

import java.util.UUID;

import static top.chr0nix.maa4j.utils.JWTUtils.SECRET;
import static top.chr0nix.maa4j.maa.MaaInstance.maaCore;

@Component
public class RunScript implements ApplicationRunner {

    @Value("${maa4j.secret:}")
    private String secret;

    @Value("${maa4j.maa-path}")
    private String maaPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("maa4j初始化...");

        if (!secret.equals("")){
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
