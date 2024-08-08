package top.chr0nix.maa4j.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Maa4j API")
                        .description("Maa4j服务器API文档，仅供开发使用，有关MAA任务参数请参考MAA集成文档")
                        .version("v1.1.1")
                        .contact(new Contact()
                                .name("Chr0nix")
                                .email("czxxxcyz@163.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("MAA集成文档")
                        .url("https://maa.plus/docs/zh-cn/protocol/integration.html"));
    }

}
