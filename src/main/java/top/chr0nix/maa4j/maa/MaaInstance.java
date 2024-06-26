package top.chr0nix.maa4j.maa;

import com.sun.jna.Pointer;

public class MaaInstance {

    public static MaaCore maaCore;
    private final Pointer pointer;
    private final String adbPath;
    private final String host;
    private final String config;
    private final String account;

    public MaaInstance(
            String account,
            String adbPath,
            String host,
            String config,
            MaaCore.AsstApiCallback callback
    ) {
        this.account = account;
        this.adbPath = adbPath;
        this.host = host;
        this.config = config;
        this.pointer = maaCore.AsstCreateEx(callback, account);
    }

    public boolean connect(){
        maaCore.AsstConnect(pointer, adbPath, host, config);
        return true;
    }

    public int appendTask(String type, String params){
        return maaCore.AsstAppendTask(pointer, type, params);
    }

    public void start(){
        maaCore.AsstStart(pointer);
    }

}
