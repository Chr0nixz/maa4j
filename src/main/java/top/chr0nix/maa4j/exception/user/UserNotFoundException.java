package top.chr0nix.maa4j.exception.user;

import top.chr0nix.maa4j.constant.ResponseCodeConstants;
import top.chr0nix.maa4j.exception.Maa4jWebException;
import top.chr0nix.maa4j.message.UserMessages;

public class UserNotFoundException extends Maa4jWebException {

    public UserNotFoundException(String message){
        super(message);
        this.code = ResponseCodeConstants.NOT_FOUND;
    }

    public UserNotFoundException(){
        super(UserMessages.UNKNOWN_USER);
        this.code = ResponseCodeConstants.NOT_FOUND;
    }

}
