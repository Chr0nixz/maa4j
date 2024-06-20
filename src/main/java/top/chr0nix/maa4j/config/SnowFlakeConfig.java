package top.chr0nix.maa4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.chr0nix.maa4j.utils.SnowFlake;

@Configuration
public class SnowFlakeConfig {

    @Bean
    public SnowFlake snowFlake(){
        return new SnowFlake(1, 1, 1);
    }

}
