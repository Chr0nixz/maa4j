package top.chr0nix.maa4j.exception.config;

import top.chr0nix.maa4j.constant.ResponseCodeConstants;
import top.chr0nix.maa4j.exception.Maa4jWebException;
import top.chr0nix.maa4j.message.ConfigMessages;

public class WrongFightConfigException extends Maa4jWebException {

    public WrongFightConfigException(String message) {
        super(message);
        this.code = ResponseCodeConstants.PARAM_ERROR;
    }

    public WrongFightConfigException() {
        super(ConfigMessages.FIGHT_CONFIG_ERROR);
        this.code = ResponseCodeConstants.PARAM_ERROR;
    }

}
