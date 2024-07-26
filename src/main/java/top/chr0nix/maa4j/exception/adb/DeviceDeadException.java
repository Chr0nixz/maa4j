package top.chr0nix.maa4j.exception.adb;

import top.chr0nix.maa4j.exception.Maa4jInnerException;
import top.chr0nix.maa4j.message.AdbMessages;

public class DeviceDeadException extends Maa4jInnerException {

    public DeviceDeadException(String device){
        super(AdbMessages.DEVICE_DEAD + device);
    }

}
