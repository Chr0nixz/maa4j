package top.chr0nix.maa4j.exception.adb;

public class AdbConnectException extends RuntimeException{

    public AdbConnectException(String message){
        super(message);
    }

    public AdbConnectException(){
        super("ADB连接错误！");
    }

}
