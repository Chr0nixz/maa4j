package top.chr0nix.maa4j.exception.config;

import top.chr0nix.maa4j.constant.ResponseCodeConstants;
import top.chr0nix.maa4j.exception.Maa4jWebException;
import top.chr0nix.maa4j.message.ConfigMessages;

public class WrongRecruitConfigException extends Maa4jWebException {

    public WrongRecruitConfigException(String message) {
        super(message);
        this.code = ResponseCodeConstants.PARAM_ERROR;
    }

    public WrongRecruitConfigException() {
        super(ConfigMessages.RECRUIT_CONFIG_ERROR);
        this.code = ResponseCodeConstants.PARAM_ERROR;
    }

}
