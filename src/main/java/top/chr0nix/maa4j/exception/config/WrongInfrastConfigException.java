package top.chr0nix.maa4j.exception.config;

import top.chr0nix.maa4j.constant.ResponseCodeConstants;
import top.chr0nix.maa4j.exception.Maa4jWebException;
import top.chr0nix.maa4j.message.ConfigMessages;

public class WrongInfrastConfigException extends Maa4jWebException {

    public WrongInfrastConfigException(String message) {
        super(message);
        this.code = ResponseCodeConstants.PARAM_ERROR;
    }

    public WrongInfrastConfigException() {
        super(ConfigMessages.INFRAST_CONFIG_ERROR);
        this.code = ResponseCodeConstants.PARAM_ERROR;
    }

}
