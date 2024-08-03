package top.chr0nix.maa4j.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.ObjectUtils;
import top.chr0nix.maa4j.constant.ResponseCodeConstants;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private int code = ResponseCodeConstants.SUCCESS;

    private String msg;

    private T data;

    public static <T> Result<T> success() {
        return restResult(null, ResponseCodeConstants.SUCCESS, null);
    }

    public static <T> Result<T> success(String msg) {
        return restResult(null, ResponseCodeConstants.SUCCESS, msg);
    }

    public static <T> Result<T> success(T data, String msg) {
        return restResult(data, ResponseCodeConstants.SUCCESS, msg);
    }

    public static <T> Result<T> repeatSuccess() {
        return restResult(null, ResponseCodeConstants.REPEAT_SUCCESS, null);
    }

    public static <T> Result<T> repeatSuccess(String msg) {
        return restResult(null, ResponseCodeConstants.REPEAT_SUCCESS, msg);
    }

    public static <T> Result<T> repeatSuccess(T data, String msg) {
        return restResult(data, ResponseCodeConstants.REPEAT_SUCCESS, msg);
    }

    public static <T> Result<T> isSuccess(boolean flag) {
        return flag ? success() : failed();
    }

    public static <T> Result<T> paramError() {
        return restResult(null, ResponseCodeConstants.PARAM_ERROR, null);
    }

    public static <T> Result<T> paramError(String msg) {
        return restResult(null, ResponseCodeConstants.PARAM_ERROR, msg);
    }

    public static <T> Result<T> paramError(T data, String msg) {
        return restResult(data, ResponseCodeConstants.PARAM_ERROR, msg);
    }

    public static <T> Result<T> unauthorized() {
        return restResult(null, ResponseCodeConstants.UNAUTHORIZED, null);
    }

    public static <T> Result<T> unauthorized(String msg) {
        return restResult(null, ResponseCodeConstants.UNAUTHORIZED, msg);
    }

    public static <T> Result<T> unauthorized(T data, String msg) {
        return restResult(data, ResponseCodeConstants.UNAUTHORIZED, msg);
    }

    public static <T> Result<T> forbidden() {
        return restResult(null, ResponseCodeConstants.FORBIDDEN, null);
    }

    public static <T> Result<T> forbidden(String msg) {
        return restResult(null, ResponseCodeConstants.FORBIDDEN, msg);
    }

    public static <T> Result<T> forbidden(T data, String msg) {
        return restResult(data, ResponseCodeConstants.FORBIDDEN, msg);
    }

    public static <T> Result<T> notFound() {
        return restResult(null, ResponseCodeConstants.NOT_FOUND, null);
    }

    public static <T> Result<T> notFound(String msg) {
        return restResult(null, ResponseCodeConstants.NOT_FOUND, msg);
    }

    public static <T> Result<T> notFound(T data, String msg) {
        return restResult(data, ResponseCodeConstants.NOT_FOUND, msg);
    }

    public static <T> Result<T> failed() {
        return restResult(null, ResponseCodeConstants.FAIL, null);
    }


    public static <T> Result<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, ResponseCodeConstants.FAIL, msg);
    }

    public static <T> Result<T> failed(String msg, T data) {
        return restResult(data, ResponseCodeConstants.FAIL, msg);
    }

    public static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    @JsonIgnore
    public Boolean isDataNull() {
        return ObjectUtils.isEmpty(data);
    }

}
