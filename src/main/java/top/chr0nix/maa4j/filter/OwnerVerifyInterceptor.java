package top.chr0nix.maa4j.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.chr0nix.maa4j.annotation.OwnerVerify;
import top.chr0nix.maa4j.service.intf.UserService;
import top.chr0nix.maa4j.utils.JWTUtils;

import java.util.Objects;

@Component
public class OwnerVerifyInterceptor implements HandlerInterceptor {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        if (!(handler instanceof HandlerMethod method)) {
            return true;
        }

        String token = request.getHeader("Authorization");
        if (Objects.equals(token, "")) {
            response.setStatus(401);
            return false;
        }

        String[] uri = request.getRequestURI().split("/");
        String account = uri[uri.length - 1];

        var ownerVerify = method.getMethod().getAnnotation(OwnerVerify.class);
        if (ownerVerify != null) {
            try {
                if (JWTUtils.verifyToken(token) && Objects.equals(JWTUtils.getType(token), "user")) {
                    if (userService.hasAccount(account, JWTUtils.getId(token))) {
                        return true;
                    }
                } else {
                    response.setStatus(401);
                    return false;
                }
            } catch (RuntimeException e) {
                response.setStatus(401);
                return false;
            }
        }
        return true;

    }

}
