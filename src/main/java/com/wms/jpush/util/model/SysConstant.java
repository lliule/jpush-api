package com.wms.jpush.util.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 常量
 */
public class SysConstant {
    /**
     * 操作错误状态码
     */
    public static final String CODE_OPER_FAIL = "F";
    /**
     * 操作成功状态码
     */
    public  static final  String CODE_OPER_SUCC = "T";
    /**
     * insert 失败信息
     */
    public static final  String INSERTERR="添加数据失败!";
    public static final  String PERMISSIONERR = "对不起，你没有权限访问！";
    public static final String REQUESTERR  ="请求操作失败!";
    public static final String SYNCHROERR = "同步失败!";
    public static final String VALIDATORERR = "数据校验失败!";
    public static final String QUERYERR ="获取信息失败!";
    public static final String QUERYNOFOUNT = "找不到相关数据!";
//    public static final String QUERYLISTERR = "get messages failure!";
//    public static final String QUERYPAGEERR = "get page message failure!";
    /**
     * update 失败信息
     */
    public static final String UPDATEERR ="修改信息失败!";
    /**
     * delete 失败信息
     */
    public static final String DELETEERR = "删除信息失败!";

    public static final String SETCACHEERR ="设置缓存信息失败!";

    public static final String DELCACHEERR ="删除缓存信息失败!";

    public static final String PUSHERR="推送信息失败";
    public static Boolean FORBID = true;

    //根据库来命名错误信息邮件的标题名
    public static String LoggerTitle="";

    /*用户身份信息*/
    public static final String YI_SID="yisid";

    public static boolean TEST = false;

    public static Map<String,Object> MAP = new HashMap<String,Object>();
}
