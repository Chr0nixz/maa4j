package top.chr0nix.maa4j.exception;

public class Maa4jWebException extends RuntimeException{

    public int code;

    public Maa4jWebException(String message) {
        super(message);
    }

    //仅作自定义异常的标识

}
