package top.chr0nix.maa4j.maa;

public class MaaCallback implements MaaCore.AsstApiCallback {

    @Override
    public void callback(int msg, String detail_json, String custom_arg) {
        System.out.printf("回调msg : %s , 回调 detail_json : %s ,回调 custom_arg : %s \n", msg, detail_json, custom_arg);
    }

}
