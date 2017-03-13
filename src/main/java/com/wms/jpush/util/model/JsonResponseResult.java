package com.wms.jpush.util.model;


import org.apache.http.HttpStatus;

import java.util.List;

/**
 * 说明.
 * 自定义接口返回类
 */
public class JsonResponseResult {

    /**
     * 说明.
     * 操作状态：默认为200
     * 查询不到内容：204
     * 系统异常：500
     * 未登录：401
     * 登录信息校验失败无权限：403
     * 参数检验失败：400
     */
    private Integer status = HttpStatus.SC_OK;

    /**
     * 说明.
     *错误信息： 默认“” ；
     */
    private String errorMsg = "";

    /**
     * 说明.
     *状态码
     * T 操作成功
     * F 操作失败
     */
    private  String code = "T";

    /**说明.
     *结果信息
     */
    private  Object data = SysConstant.MAP;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public JsonResponseResult() {
    }

    public JsonResponseResult(Integer status, String errorMsg, String code, Object data) {
        this.status = status;
        this.errorMsg = errorMsg;
        this.code = code;
        this.data = data;
    }

    public static  JsonResponseResult validatorErr(JsonResponseResult result) {
        result.setErrorMsg(SysConstant.VALIDATORERR);
        result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        result.setCode(SysConstant.CODE_OPER_FAIL);
        result.setData("");
        return result;
    }

    public static Object validatorErr(JsonResponseResult result, List<String> list) {
        result.setErrorMsg(SysConstant.VALIDATORERR);
        result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        result.setCode(SysConstant.CODE_OPER_FAIL);
        result.setData(list);
        return result;
    }
}
