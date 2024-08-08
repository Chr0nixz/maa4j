package top.chr0nix.maa4j.maa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.chr0nix.maa4j.exception.adb.AdbConnectException;
import top.chr0nix.maa4j.exception.adb.DeviceDeadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
@Slf4j
public class AdbManager {

    @Value("${maa4j.adb_path}")
    private String adbPath;
    private final Runtime runtime;

    public AdbManager() {
        this.adbPath = this.adbPath + " ";
        this.runtime = Runtime.getRuntime();
    }

    public boolean isAdbAvailable() throws IOException {
        BufferedReader reader = null;
        try {
            Process version = this.runtime.exec(new String[]{adbPath, "version"});
            reader = new BufferedReader(new InputStreamReader(version.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Android Debug Bridge")) {
                    return true;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return false;
    }

    public boolean connect(String address) {
        BufferedReader reader;
        try {
            Process connect = runtime.exec(new String[]{adbPath, "connect", address});
            reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!(line.startsWith("connect") || line.startsWith("already"))) {
                    throw new AdbConnectException(line);
                }
            }
            return isAlive(address);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean isAlive(String address) {
        BufferedReader reader;
        try {
            Process check = runtime.exec(new String[]{adbPath, "devices"});
            reader = new BufferedReader(new InputStreamReader(check.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(address)) {
                    if (!(Objects.equals(line.split("\\t")[1], "device"))){
                        throw new DeviceDeadException(address);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        log.info("{} alive", address);
        return true;
    }

    public HashMap<String, Integer> isAlive(HashMap<String, Integer> deviceMap) {
        BufferedReader reader;
        HashMap<String, String> outMap = new HashMap<>();
        try {
            Process check = runtime.exec(new String[]{adbPath, "devices"});
            reader = new BufferedReader(new InputStreamReader(check.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null && !line.startsWith("List")) {
                String[] strings = line.split("\\t");
                outMap.put(strings[0], strings[1]);
            }
            for (Map.Entry<String, Integer> entry : deviceMap.entrySet()) {
                if (Objects.equals(outMap.get(entry.getKey()), "device")) {
                    deviceMap.put(entry.getKey(), 1);
                } else {
                    deviceMap.put(entry.getKey(), 0);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return deviceMap;
        }
        return deviceMap;
    }

}
