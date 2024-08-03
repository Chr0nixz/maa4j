package top.chr0nix.maa4j.filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.chr0nix.maa4j.annotation.Authority;
import top.chr0nix.maa4j.annotation.UserLogin;
import top.chr0nix.maa4j.constant.AdminAuthority;
import top.chr0nix.maa4j.utils.JWTUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Objects;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Value("${maa4j.ignore_auth:false}")
    private boolean ignoreAuth;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        if (!(handler instanceof HandlerMethod method)) {
            return true;
        }

        if (ignoreAuth) {
            return true;
        }

        String token = request.getHeader("Authorization");

        if (Objects.equals(token, "")) {
            response.setStatus(401);
            return false;
        }

        var func = method.getMethod();

        //用户登录
        var userLogin = func.getAnnotation(UserLogin.class);
        if (userLogin != null) {
            if (JWTUtils.verifyToken(token) && Objects.equals(JWTUtils.getType(token), "user")) {
                return true;
            } else {
                response.setStatus(401);
                return false;
            }
        }

        //管理员权限检查
        var authority = func.getAnnotation(Authority.class);
        if (authority != null) {
            if (!JWTUtils.verifyToken(token)) {
                response.setStatus(401);
                return false;
            }
            if (!Objects.equals(JWTUtils.getType(token), "admin")){
                response.setStatus(401);
                return false;
            }
            String authString = JWTUtils.getAuth(token);
            if (authString == null) {
                return false;
            }
            String[] requires = authority.value();
            if (Objects.equals(requires[0], "")) {
                String string = "can" + func.getName();
                if (AdminAuthority.ALL.contains(string)) {
                    requires = new String[]{string};
                } else {
                    requires = new String[]{AdminAuthority.SUPER};
                }
            }
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, Boolean>>(){}.getType();
            HashMap<String, Boolean> authMap = gson.fromJson(authString, type);
            if (authMap.get(AdminAuthority.SUPER)) {
                return true;
            }
            for (String str : requires) {
                if (!Objects.equals(authMap.get(str), true)) {
                    return false;
                }
            }
        }
        return true;
    }

}
