package top.chr0nix.maa4j.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.chr0nix.maa4j.annotation.UserLogin;
import top.chr0nix.maa4j.utils.JWTUtils;

import java.util.Objects;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Value("${maa4j.ignore_auth:false}")
    private boolean ignoreAuth;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler){
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (ignoreAuth) {
            return true;
        }

        String token = request.getHeader("Authorization");

        if (token == "") {
            return false;
        }

        HandlerMethod method = (HandlerMethod) handler;

        var userLogin = method.getMethod().getAnnotation(UserLogin.class);
        if (userLogin != null) {
            if (JWTUtils.verifyToken(token) && Objects.equals(JWTUtils.getType(token), "user")) {
                return true;
            } else {
                response.setStatus(401);
                return false;
            }
        }
        return true;
    }

}
