package top.chr0nix.maa4j.maa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class AdbManager {

    @Value("${maa4j.adb-path}")
    private String adbPath;
    private Runtime runtime;

    public AdbManager() {
        this.adbPath = this.adbPath + " ";
        this.runtime = Runtime.getRuntime();
    }

    public boolean isAdbAvailable() throws IOException {
        BufferedReader reader = null;
        try {
            Process version = this.runtime.exec( adbPath + "version");
            reader = new BufferedReader(new InputStreamReader(version.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Android Debug Bridge")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return false;
    }

}
