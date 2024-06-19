package top.chr0nix.maa4j.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import top.chr0nix.maa4j.entity.UserEntity;

import java.util.Date;

public class JWTUtils {

    @Value("$maa4j.secret:")
    public static String SECRET;

    private static final Long EXPIRATION = 1000L * 60 * 60 * 24 * 30;

    public static String generateTokenForUser(UserEntity userEntity){
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", userEntity.getId())
                .withClaim("name", userEntity.getName())
                .withClaim("typr", "user")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION));
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean verifyToken(String token){
        try {
            if (token != null) {
                JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static Long getId(String token){
        assert token != null;
        return JWT.decode(token.substring(7)).getClaim("id").asLong();
    }

    public static String getName(String token){
        assert token!= null;
        return JWT.decode(token.substring(7)).getClaim("name").asString();
    }

    public static String getType(String token){
        assert token != null;
        return JWT.decode(token).getClaim("type").asString();
    }

}
