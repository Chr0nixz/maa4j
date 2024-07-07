package top.chr0nix.maa4j.exception.config;

import top.chr0nix.maa4j.message.ConfigMessages;

public class WrongRecruitConfigException extends RuntimeException{

    public WrongRecruitConfigException(String message) {
        super(message);
    }

    public WrongRecruitConfigException() {
        super(ConfigMessages.RECRUIT_CONFIG_ERROR);
    }

}
