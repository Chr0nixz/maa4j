package top.chr0nix.maa4j.exception.config;

import top.chr0nix.maa4j.message.ConfigMessages;

public class WrongFightConfigException extends RuntimeException{

    public WrongFightConfigException(String message) {
        super(message);
    }

    public WrongFightConfigException() {
        super(ConfigMessages.FIGHT_CONFIG_ERROR);
    }

}
