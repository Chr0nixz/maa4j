package top.chr0nix.maa4j.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.chr0nix.maa4j.exception.Maa4jWebException;
import top.chr0nix.maa4j.utils.Result;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Maa4jWebException.class)
    public Result<String> handleMaa4jException(Maa4jWebException e) {
        if (e != null) {
            return Result.restResult(null, e.code, e.getMessage());
        } else {
            return Result.failed("未知错误！");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(RuntimeException e) {
        if (e instanceof DataIntegrityViolationException) {
            Pattern r = Pattern.compile("(?<=').+?(?=')");
            Matcher m = r.matcher(e.getMessage());
            ArrayList<String> arrayList = new ArrayList<>();
            while (m.find()) {
                arrayList.add(m.group());
            }
            Result<String> result;
            switch (arrayList.get(2)) {
                case "user.name" -> result = Result.failed("用户名重复！");
                case "account.account" -> result = Result.failed("账户已存在！");
                default -> result = Result.failed("重复！");
            }
            return result;
        }
        return Result.failed("未知错误!" + e.getClass().getSimpleName());
    }

}
