package top.chr0nix.maa4j.utils.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "maa4j")
public class Maa4jProperties {

    String secret;

    boolean ignore_auth;

    String maa_path;

    String adb_path;

    List<String> devices;

}
