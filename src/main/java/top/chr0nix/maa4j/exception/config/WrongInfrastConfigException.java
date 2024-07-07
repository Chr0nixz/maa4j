package top.chr0nix.maa4j.exception.config;

import top.chr0nix.maa4j.message.ConfigMessages;

public class WrongInfrastConfigException extends RuntimeException {

    public WrongInfrastConfigException(String message) {
        super(message);
    }

    public WrongInfrastConfigException() {
        super(ConfigMessages.INFRAST_CONFIG_ERROR);
    }

}
