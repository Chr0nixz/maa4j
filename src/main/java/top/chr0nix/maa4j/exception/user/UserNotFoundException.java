package top.chr0nix.maa4j.exception.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(){
        super("用户不存在！");
    }

}
