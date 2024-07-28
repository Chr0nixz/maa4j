package top.chr0nix.maa4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.chr0nix.maa4j.filter.JwtTokenInterceptor;
import top.chr0nix.maa4j.filter.OwnerVerifyInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private final JwtTokenInterceptor jwtTokenInterceptor;
    private final OwnerVerifyInterceptor ownerVerifyInterceptor;

    @Autowired
    public WebConfig(JwtTokenInterceptor jwtTokenInterceptor,
                     OwnerVerifyInterceptor ownerVerifyInterceptor) {
        this.jwtTokenInterceptor = jwtTokenInterceptor;
        this.ownerVerifyInterceptor = ownerVerifyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(ownerVerifyInterceptor)
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}
