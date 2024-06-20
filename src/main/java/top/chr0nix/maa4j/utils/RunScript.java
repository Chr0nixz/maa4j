package top.chr0nix.maa4j.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static top.chr0nix.maa4j.utils.JWTUtils.SECRET;

@Component
public class RunScript implements ApplicationRunner {

    @Value("${maa4j.secret:}")
    private String secret;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("maa4j初始化...");
        if (!secret.equals("")){
            SECRET = secret;
        } else {
            SECRET = UUID.randomUUID().toString().replace("-", "");
            System.out.println("随机秘钥:" + SECRET);
        }
        System.out.println("maa4j初始化完成！");
    }
}
