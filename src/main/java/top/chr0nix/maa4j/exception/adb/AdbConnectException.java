package top.chr0nix.maa4j.exception.adb;

import top.chr0nix.maa4j.exception.Maa4jInnerException;
import top.chr0nix.maa4j.message.AdbMessages;

public class AdbConnectException extends Maa4jInnerException {

    public AdbConnectException(String message){
        super(message);
    }

    public AdbConnectException(){
        super(AdbMessages.ADB_CONNECT_ERROR);
    }

}
