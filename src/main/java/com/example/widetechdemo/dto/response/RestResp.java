package com.example.widetechdemo.dto.response;

import com.example.widetechdemo.constants.ErrorCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Objects;

@Getter
public class RestResp<T> {

    /**
     * response code
     */
    @Schema(description = "errorCode，00000-no error")
    private String code;

    /**
     * response msg
     */
    @Schema(description = "response msg")
    private String message;

    /**
     * response data
     */
    @Schema(description = "response data")
    private T data;

    @Schema(description = "status")
    private Status status;

    private RestResp() {
        this.code = ErrorCodeEnum.OK.getCode();
        this.message = ErrorCodeEnum.OK.getMessage();
        this.status=Status.SUCCESS;
    }

    private RestResp(ErrorCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.status = Status.FAIL;
    }

    private RestResp(ErrorCodeEnum errorCode, T data) {
        this(errorCode);
        this.data = data;
        this.status = Status.FAIL;
    }

    private RestResp(T data, Status status) {
        this();
        this.data = data;
        this.status = status;
    }

    /**
     * success,no data return
     */
    public static RestResp<Void> ok() {
        return new RestResp<>();
    }

    /**
     * success，return with data
     */
    public static <T> RestResp<T> ok(T data) {
        return new RestResp<>(data, Status.SUCCESS);
    }

    /**
     * fail
     */
    public static RestResp<Void> fail(ErrorCodeEnum errorCode) {
        return new RestResp<>(errorCode);
    }

    public static <T> RestResp<T> fail(ErrorCodeEnum errorCode, T data) {
        return new RestResp<>(errorCode, data);
    }


    /**
     * sys error
     */
    public static RestResp<Void> error() {
        return new RestResp<>(ErrorCodeEnum.SYSTEM_ERROR);
    }

    /**
     * test ok or not
     */
    public boolean isOk() {
        return Objects.equals(this.code, ErrorCodeEnum.OK.getCode());
    }

    @Override
    public String toString() {
        return "RestResp{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
