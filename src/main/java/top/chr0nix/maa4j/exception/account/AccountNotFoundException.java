package top.chr0nix.maa4j.exception.account;

import top.chr0nix.maa4j.constant.ResponseCodeConstants;
import top.chr0nix.maa4j.exception.Maa4jWebException;
import top.chr0nix.maa4j.message.AccountMessages;

public class AccountNotFoundException extends Maa4jWebException {

    public AccountNotFoundException(String message){
        super(message);
        this.code = ResponseCodeConstants.NOT_FOUND;
    }

    public AccountNotFoundException(){
        super(AccountMessages.ACCOUNT_NOT_FOUND);
        this.code = ResponseCodeConstants.NOT_FOUND;
    }

}
