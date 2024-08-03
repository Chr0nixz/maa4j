package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.maa.AdbManager;
import top.chr0nix.maa4j.service.intf.DeviceService;
import top.chr0nix.maa4j.utils.DynamicInfo;

import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DynamicInfo dynamicInfo;
    private final AdbManager adbManager;

    @Autowired
    public DeviceServiceImpl(DynamicInfo dynamicInfo,
                             AdbManager adbManager) {
        this.dynamicInfo = dynamicInfo;
        this.adbManager = adbManager;
    }

    @Override
    public boolean addDevice(String device) {
        return false;
    }

    @Override
    public boolean deleteDevice(String device) {
        return false;
    }

    @Override
    public String applyDevice() {
        for (Map.Entry<String, Integer> entry : dynamicInfo.getDeviceStatusMap().entrySet()) {
            if (entry.getValue() == 1) {
                String device = entry.getKey();
                dynamicInfo.getDeviceStatusMap().put(device, 2);
                return device;
            }
        }
        return "";
    }

    @Override
    public void releaseDevice(String device) {
        dynamicInfo.getDeviceStatusMap().put(device, 1);
    }

    @Override
    public void monitorDevice() {
        if (!dynamicInfo.getDeviceStatusMap().isEmpty()) {
            for (Map.Entry<String, Integer> entry : dynamicInfo.getDeviceStatusMap().entrySet()) {
                if (adbManager.isAlive(entry.getKey())) {
                    dynamicInfo.getDeviceStatusMap().put(entry.getKey(), 1);
                }
            }
        }
    }

    @Override
    public int getDeviceCount() {
        return dynamicInfo.getDeviceStatusMap().size();
    }

    @Override
    public int getDeviceCountFree() {
        int count = 0;
        for (Map.Entry<String, Integer> entry : dynamicInfo.getDeviceStatusMap().entrySet()) {
            if (entry.getValue() == 1 ){
                count++;
            }
        }
        return count;
    }

}
