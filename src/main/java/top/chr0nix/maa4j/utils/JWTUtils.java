package top.chr0nix.maa4j.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import top.chr0nix.maa4j.entity.AdminEntity;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.entity.converter.AuthorityHashMapConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class JWTUtils {

    public static String SECRET;

    private static final Long EXPIRATION = 1000L * 60 * 60 * 24 * 30;

    private static final HashMap<Long, String> loginKeysCache = new HashMap<>();

    public static String generateTokenForUser(UserEntity userEntity) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", userEntity.getId())
                .withClaim("name", userEntity.getName())
                .withClaim("type", "user")
                .withClaim("loginKey", userEntity.getLoginKey())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION));
        loginKeysCache.put(userEntity.getId(), userEntity.getLoginKey());
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static String generateTokenForAdmin(AdminEntity adminEntity) {
        JWTCreator.Builder builder = JWT.create();
        AuthorityHashMapConverter converter = new AuthorityHashMapConverter();
        builder.withClaim("id", adminEntity.getId())
                .withClaim("name", adminEntity.getName())
                .withClaim("type", "admin")
                .withClaim("loginKey", adminEntity.getLoginKey())
                .withClaim("auth", converter.convertToDatabaseColumn(adminEntity.getAuthority()))
                .withExpiresAt((new Date(System.currentTimeMillis() + EXPIRATION)));
        loginKeysCache.put(adminEntity.getId(), adminEntity.getLoginKey());
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean verifyToken(String token){
        try {
            if (token != null) {
                JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
                return Objects.equals(loginKeysCache.get(getId(token)), getLoginKey(token));
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static Long getId(String token){
        assert token != null;
        return JWT.decode(token).getClaim("id").asLong();
    }

    public static String getName(String token){
        assert token != null;
        return JWT.decode(token).getClaim("name").asString();
    }

    public static String getType(String token){
        assert token != null;
        return JWT.decode(token).getClaim("type").asString();
    }
    public static String getLoginKey(String token){
        assert token != null;
        return JWT.decode(token).getClaim("loginKey").asString();
    }

    public static String getAuth(String token) {
        assert token != null;
        if (!Objects.equals(getType(token), "admin")) {
            return null;
        }
        return JWT.decode(token).getClaim("auth").asString();
    }

}
