package top.chr0nix.maa4j.utils;

import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Encoder {

    public static String MD5(String string){
        return DigestUtils.md5DigestAsHex(string.getBytes(StandardCharsets.UTF_8));
    }

    static KeyGenerator aesGenerator;

    static {
        try {
            aesGenerator = KeyGenerator.getInstance("AES");
            aesGenerator.init(256);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Key generateKey(){
        return aesGenerator.generateKey();
    }

    public static String aesEncrypt(String plainText, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(plainText.getBytes("UTF-8"));
        return result.toString();
    }

    public static String aesDecrypt(String cipherText, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(cipherText.getBytes("UTF-8"));
        return result.toString();
    }

}
