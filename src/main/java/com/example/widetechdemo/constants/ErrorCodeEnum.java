package com.example.widetechdemo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error code enumeration class.
 * <p>
 * Error codes are of string type, consisting of 5 digits, divided into two parts: the source of the error + a four-digit number. The error sources are categorized as A/B/C, where A represents errors originating from users, such as parameter errors, users using outdated versions, or user payment timeouts; B represents errors originating from the current system, often related to business logic errors or poor program robustness; C represents errors originating from third-party services, such as CDN service errors, message delivery timeouts, etc. The four-digit numbers range from 0001 to 9999, with a reserved interval of 100 between major categories.
 * <p>
 * Error codes are divided into first-level macro error codes, second-level macro error codes, and third-level macro error codes. In scenarios where it is challenging to determine a more specific error, you can directly use first-level macro error codes.
 * @author dingdian
 * @date 2023/12/07
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * 正确执行后的返回
     */
    OK("00000", "success"),

    /**
     * 一级宏观错误码，用户端错误
     */
    USER_ERROR("A0001", "client error"),


    USER_EXISTED("A0002", "user already existed"),

    /**
     * 用户账号不存在
     */
    USER_NOT_EXIST("A0003", "user not exist"),


    /**
     * 二级宏观错误码，用户请求参数错误
     */
    USER_REQUEST_PARAM_ERROR("A0004", "request param incorrect"),
    //USER_EMAIL_EXISTED("A0005", "user email already existed"),



    /**
     * 一级宏观错误码，系统执行出错
     */
    SYSTEM_ERROR("B0001", "sys error"),

    /**
     * 二级宏观错误码，系统执行超时
     */
    SYSTEM_TIMEOUT_ERROR("B0002", "sys time out"),

    /**
     * 一级宏观错误码，调用第三方服务出错
     */
    THIRD_SERVICE_ERROR("C0001", "third party sevice invoke error"),

    /**
     * 一级宏观错误码，中间件服务出错
     */
    MIDDLEWARE_SERVICE_ERROR("C0002", "middleware service error");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 中文描述
     */
    private final String message;

}
