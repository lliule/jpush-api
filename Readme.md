1、使用jpush-api，添加依赖
<dependency>
   <groupId>com.weichaishi.jpush.api</groupId>
   <artifactId>api.jpush.weichaishi</artifactId>
   <version>1.0-SNAPSHOT</version>
</dependency>
2、在spring中添加扫描项目包；
<context:component-scan base-package="com.wms.jpush"/>

3、配置appkey:
IOS: -- APPkey :"JPUSH_APP_KEY_WCS"; APPsecret:"JPUSH_MASTER_SECRET_WCS"

ANDRIOD: -- APPkey :"JPUSH_APP_KEY_YIYU"; APPsecret:"JPUSH_MASTER_SECRET_YIYU"

4、注入JPushApi,调用接口；

   （1）：pushAndroidMessageByAliases(PushModel pushModel); -- 推送消息到安卓设备
         param:{"andriodAliases":["3541919","3700937"...], -- andriod推送设备唯一识别，可多个
               "title":"happy new year", -- 消息头 andriod才有
               "content":"新年将近，微差事祝大家新年快乐！",-- 推行消息体
               "diviceType":2 -- 设备类型 0--IOS，2--android
               }
   （2）：pushIosMessageByAliases(PushModel pushModel); -- 推送消息到IOS
         param:{"iosAliases":["3541919"], -- ios推送设备唯一识别，可多个
               "content":"新年将近，微差事祝大家新年快乐！",
               "diviceType":0}
         return:{"status":200,"errorMsg":"","code":"T","data":{}}