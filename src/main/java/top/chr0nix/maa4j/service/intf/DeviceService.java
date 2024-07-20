package top.chr0nix.maa4j.service.intf;

public interface DeviceService {

    boolean addDevice(String device);

    boolean deleteDevice(String device);

    String applyDevice();

    void releaseDevice(String device);

    void monitorDevice();

    int getDeviceCount();

    int getDeviceCountFree();

}
