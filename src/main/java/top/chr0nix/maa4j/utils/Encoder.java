package top.chr0nix.maa4j.utils;

import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Encoder {

    public static String MD5(String string){
        return DigestUtils.md5DigestAsHex(string.getBytes(StandardCharsets.UTF_8));
    }

    static KeyGenerator aesGenerator;

    public static SecretKey generateKey(String seed) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        aesGenerator = KeyGenerator.getInstance("AES");
        aesGenerator.init(new SecureRandom(seed.getBytes(StandardCharsets.UTF_8)));
        return aesGenerator.generateKey();
    }

    public static String aesEncrypt(String plainText, String seed) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(seed));
        byte[] result = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Arrays.toString(result);
    }

    public static String aesDecrypt(String cipherText, String seed) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, generateKey(seed));
        byte[] result = cipher.doFinal(cipherText.getBytes(StandardCharsets.UTF_8));
        return Arrays.toString(result);
    }

}
