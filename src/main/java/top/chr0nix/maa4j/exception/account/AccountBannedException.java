package top.chr0nix.maa4j.exception.account;

import top.chr0nix.maa4j.constant.ResponseCodeConstants;
import top.chr0nix.maa4j.exception.Maa4jWebException;
import top.chr0nix.maa4j.message.AccountMessages;

public class AccountBannedException extends Maa4jWebException {

    public AccountBannedException(String message){
        super(message);
        this.code = ResponseCodeConstants.FORBIDDEN;
    }

    public AccountBannedException(){
        super(AccountMessages.ACCOUNT_BANNED);
        this.code = ResponseCodeConstants.FORBIDDEN;
    }

}
