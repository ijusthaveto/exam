package me.ijusthaveto.exam.common;

/**
 * Created by IntelliJ IDEA.
 * @author: ijusthaveto
 * @create: 2023-11-01
 */
public enum ErrorCode {
    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    REGISTER_ERROR(40102, "注册失败", ""),
    LOGIN_ERROR(40103, "登录失败", ""),
    USERNAME_ERROR(40104, "用户名重复", ""),
    USER_NOT_EXIST_ERROR(40105, "用户不存在", ""),
    CONFIRM_ERROR(40105, "验证错误", ""),
    SYSTEM_ERROR(50000, "系统内部异常", "");

    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码描述（详情）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
