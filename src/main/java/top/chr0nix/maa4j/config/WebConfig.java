package top.chr0nix.maa4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.chr0nix.maa4j.filter.JwtTokenInterceptor;
import top.chr0nix.maa4j.filter.OwnerVerifyInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private JwtTokenInterceptor jwtTokenInterceptor;
    private OwnerVerifyInterceptor ownerVerifyInterceptor;

    @Autowired
    public void setJwtTokenInterceptor(JwtTokenInterceptor jwtTokenInterceptor) {
        this.jwtTokenInterceptor = jwtTokenInterceptor;
    }

    @Autowired
    public void setOwnerVerifyInterceptor(OwnerVerifyInterceptor ownerVerifyInterceptor) {
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
