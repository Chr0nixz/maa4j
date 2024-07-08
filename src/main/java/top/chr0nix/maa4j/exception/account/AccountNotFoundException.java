package top.chr0nix.maa4j.exception.account;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message){
        super(message);
    }

    public AccountNotFoundException(){
        super("账号不存在！");
    }

}
