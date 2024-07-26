package top.chr0nix.maa4j.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.chr0nix.maa4j.exception.Maa4jWebException;
import top.chr0nix.maa4j.utils.Result;

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
            return Result.failed("重复！");
        }
        return Result.failed("未知错误!" + e.getClass().getSimpleName());
    }

}
