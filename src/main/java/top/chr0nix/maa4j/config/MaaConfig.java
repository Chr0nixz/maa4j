package top.chr0nix.maa4j.config;

import com.sun.jna.Native;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import top.chr0nix.maa4j.maa.MaaCore;
import top.chr0nix.maa4j.service.intf.AsyncService;
import top.chr0nix.maa4j.service.intf.MaaService;
import top.chr0nix.maa4j.utils.model.Maa4jProperties;

@Configuration
public class MaaConfig {

    private final Maa4jProperties maa4jProperties;
    private final AsyncService asyncService;

    @Autowired
    public MaaConfig(Maa4jProperties maa4jProperties,
                     AsyncService asyncService) {
        this.maa4jProperties = maa4jProperties;

        this.asyncService = asyncService;
    }

    @Bean
    public MaaCore getMaaCore() throws Exception {
        System.setProperty("jna.library.path", maa4jProperties.getMaa_path());
        MaaCore maaCore = Native.load("MaaCore", MaaCore.class);
        asyncService.maaLoadResource(maaCore);
        return maaCore;
    }



}
