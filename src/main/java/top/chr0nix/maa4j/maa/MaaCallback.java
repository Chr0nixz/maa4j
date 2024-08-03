package top.chr0nix.maa4j.maa;

import top.chr0nix.maa4j.service.intf.MaaService;

public class MaaCallback implements MaaCore.AsstApiCallback {

    private final MaaService maaService;

    public MaaCallback(MaaService maaService) {
        this.maaService = maaService;
    }

    @Override
    public void callback(int msg, String detail_json, String custom_arg) {
        System.out.printf("回调msg : %s , 回调 detail_json : %s ,回调 custom_arg : %s \n", msg, detail_json, custom_arg);
        if (msg == 3){
            maaService.destroyInstance(custom_arg);
        }
    }

}
