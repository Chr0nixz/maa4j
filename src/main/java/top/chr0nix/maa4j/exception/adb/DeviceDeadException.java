package top.chr0nix.maa4j.exception.adb;

public class DeviceDeadException extends RuntimeException{

    public DeviceDeadException(String message){
        super(message);
    }

    public DeviceDeadException(){
        super("设备掉线！");
    }

}
