package top.chr0nix.maa4j.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import top.chr0nix.maa4j.entity.UserEntity;

import java.util.Date;

public class JWTUtils {

    public static String SECRET;

    private static final Long EXPIRATION = 1000L * 60 * 60 * 24 * 30;

    public static String generateTokenForUser(UserEntity userEntity){
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", userEntity.getId())
                .withClaim("name", userEntity.getName())
                .withClaim("type", "user")
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
        return JWT.decode(token).getClaim("id").asLong();
    }

    public static String getName(String token){
        assert token!= null;
        return JWT.decode(token).getClaim("name").asString();
    }

    public static String getType(String token){
        assert token != null;
        return JWT.decode(token).getClaim("type").asString();
    }

}
