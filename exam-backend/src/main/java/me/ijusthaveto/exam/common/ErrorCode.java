package me.ijusthaveto.exam.common;

import me.ijusthaveto.exam.constant.ResultConstant;

/**
 * Created by IntelliJ IDEA.
 *
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
    QUESTION_ADD_ERROR(40106, ResultConstant.ADD_QUESTION_FAILED, ""),
    QUESTION_REMOVE_ERROR(40107, ResultConstant.REMOVE_QUESTION_ERROR, ""),
    QUESTION_NOT_EXISTS(40108, ResultConstant.QUESTION_NOT_EXISTS, ""),
    ADMIN_NOT_EXISTS(40109, ResultConstant.ADMIN_NOT_EXISTS, ""),
    EXAM_SUM_SCORE_ERROR(40110, ResultConstant.EXAM_SCORE_ERROR, ""),
    BANK_IMPORT_FILE_ERROR(40111, ResultConstant.FILE_ERROR, ""),
    BANK_REMOVE_ERROR(40112, ResultConstant.REMOVE_BANK_ERROR, ""),
    EXAM_NOT_START_ERROR(40113, ResultConstant.EXAM_NOT_START_ERROR, ""),
    SINGLE_QUESTION_NUM_ERROR(40114, ResultConstant.SINGLE_QUESTION_NUM_ERROR, ""),
    MULTIPLE_QUESTION_NUM_ERROR(40115, ResultConstant.MULTIPLE_QUESTION_NUM_ERROR, ""),
    BOOL_QUESTION_NUM_ERROR(40116, ResultConstant.BOOL_QUESTION_NUM_ERROR, ""),
    CREATE_CLASS_ERROR(40117, ResultConstant.CREATE_CLASS_ERROR, ""),
    USER_IMPORT_ERROR(40118, ResultConstant.USER_IMPORT_ERROR, ""),
    AUTO_UPDATE_TASK_ERROR(40119, ResultConstant.AUTO_UPDATE_TASK_ERROR, ""),
    ENTER_EXAM_ERROR(40120, ResultConstant.ENTER_EXAM_ERROR, ""),
    REPEAT_EXAM_ERROR(40121, ResultConstant.REPEAT_EXAM_ERROR, ""),
    PARSE_STRING_DATE_ERROR(40122, ResultConstant.PARSE_STRING_DATE_ERROR, ""),
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
