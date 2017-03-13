package com.wms.jpush.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import com.wms.jpush.service.model.PushModel;
import com.wms.jpush.util.model.JsonResponseResult;
import com.wms.jpush.util.model.SysConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class JPushService {
    private static final Logger LOG = LoggerFactory.getLogger(JPushService.class);

    @Autowired
    private PropertiesService propertiesService ;

    private static final String NORESPONSEERR = "推送消息失败，无响应信息";

    /**
     * send message to android by alias
     * @param pushModel pushModel
     * @return JsonResponseResult
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    public JsonResponseResult pushAndroidMessageByAliases(PushModel pushModel) throws APIConnectionException, APIRequestException {
        String[] aliases = pushModel.getAndriodAliases();
        String content = pushModel.getContent();
        String title = pushModel.getTitle();
        JPushClient jPushClient = propertiesService.createAndriosJPushClient(pushModel.isApnsProduction());
        int size = aliases.length;
        HashMap<String, Object> msg = new HashMap<String, Object>();
        ArrayList<String> errorList = new ArrayList<String>();
        ArrayList<String> successList = new ArrayList<String>();
        for (int i = 0;i<size;){
            String[] subAliases;
            if (size < 500){
                subAliases = Arrays.copyOfRange(aliases, i, i + size);
            }else{
                subAliases = Arrays.copyOfRange(aliases, i, i + 500);
            }
            PushResult pushResult = jPushClient.sendAndroidNotificationWithAlias(title, content, null, subAliases);
            String result = StringUtils.join(subAliases, ',');
            if(pushResult == null ){
                LOG.error("消息推送失败，aliases= {},content = {},result = {}", aliases, content,null);
                errorList.add(result);
            }else if( pushResult.getResponseCode()!= 200){
                LOG.error("消息推送失败，aliases= {},content = {},result = {}", aliases, content,pushResult.getOriginalContent());
                errorList.add(result);
            }else{
                LOG.info("消息推送成功，aliases= {},content = {},result={}", aliases, content,pushResult.getOriginalContent());
                successList.add(result);
            }
            i += 500;
            LOG.info("time == {}", System.currentTimeMillis());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                LOG.error("线程等待 50 ms 失败!");
            }
            LOG.info("time == {}", System.currentTimeMillis());
        }

        msg.put("errorAlias",errorList);
        msg.put("successAlias",successList);
        return new JsonResponseResult(HttpStatus.SC_OK,"", SysConstant.CODE_OPER_SUCC,msg);
    }

    private JsonResponseResult getJsonResponseResult(String[] aliases, String content, PushResult pushResult) {
        if (pushResult == null ) {
            LOG.error("消息推送失败，aliases= {},content = {}", aliases, content);
            return new JsonResponseResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, SysConstant.PUSHERR, SysConstant.CODE_OPER_FAIL, NORESPONSEERR);
        }else if (pushResult.getResponseCode() != 200){
            return new JsonResponseResult(pushResult.getResponseCode(), SysConstant.PUSHERR, SysConstant.CODE_OPER_FAIL, pushResult.getOriginalContent());
        }
        LOG.info("消息推送成功，aliases= {},content = {}", aliases, content);
        return new JsonResponseResult(pushResult.getResponseCode(), "", SysConstant.CODE_OPER_SUCC, pushResult.getOriginalContent());
    }

    /**
     * send message to ios by alias
     * @param pushModel pushModel
     * @return JsonResponseResult
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    public JsonResponseResult pushIosMessageByAlias(PushModel pushModel) throws APIConnectionException, APIRequestException {
        String[] aliases = pushModel.getIosAliases();
        String content = pushModel.getContent();
//        String title = pushModel.getTitle();
        JPushClient jPushClient = propertiesService.createIosJPushClient(pushModel.isApnsProduction());
        int size = aliases.length;
        HashMap<String, Object> msg = new HashMap<String, Object>();
        ArrayList<String> errorList = new ArrayList<String>();
        ArrayList<String> successList = new ArrayList<String>();
        for (int i = 0;i<size;){
            String[] subAliases;
            if (size < 500){
                subAliases = Arrays.copyOfRange(aliases, i, i + size);
            }else{
                subAliases = Arrays.copyOfRange(aliases, i, i + 500);
            }
            PushResult pushResult = jPushClient.sendIosNotificationWithAlias(content, null, aliases);
            String result = StringUtils.join(subAliases, ',');
            if(pushResult == null ){
                LOG.error("消息推送失败，aliases= {},content = {},result = {}", aliases, content,null);
                errorList.add(result);
            }else if( pushResult.getResponseCode()!= 200){
                LOG.error("消息推送失败，aliases= {},content = {},result = {}", aliases, content,pushResult.getOriginalContent());
                errorList.add(result);
            }else{
                LOG.info("消息推送成功，aliases= {},content = {},result = {}", aliases, content,pushResult.getOriginalContent());
                successList.add(result);
            }
            i += 500;
            LOG.info("time == {}", System.currentTimeMillis());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                LOG.error("线程等待 50 ms 失败!");
            }
            LOG.info("time == {}", System.currentTimeMillis());
        }

        msg.put("errorAlias",errorList);
        msg.put("successAlias",successList);
        return new JsonResponseResult(HttpStatus.SC_OK,"",SysConstant.CODE_OPER_SUCC,msg);
    }

    /**
     * send message whatever they are platform
     *
     * @param pushModel pushModels
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    public void pushMessageByAliases(PushModel pushModel) throws APIConnectionException, APIRequestException, InterruptedException {
//        JPushClient jPushClient = propertiesService.cr(pushModel.isApnsProduction());
        String[] andriodAliases = pushModel.getAndriodAliases();
        String[] iosAliases = pushModel.getIosAliases();
        boolean androidFlag = true;
        boolean iosFlag = true;
        if(andriodAliases != null && andriodAliases.length > 0){
            JsonResponseResult result = pushAndroidMessageByAliases(pushModel);
            if (result.getStatus() != 200){
                androidFlag = false;
            }
        }
        if(iosAliases != null && iosAliases.length > 0){
            JsonResponseResult result = pushIosMessageByAlias(pushModel);
            if(result.getStatus() != 200){
                iosFlag = false;
            }
        }
        if (!androidFlag){
            if (!iosFlag){
                LOG.error("Android 和 Ios 都有推送失败的用户!");
            }else {
                LOG.error("Android有推送失败的用户!");
            }
        }
        LOG.info("推送成功!");
    }

    /**
     * 给所有设备推送消息
     * @param pushModel pushModel
     * @return JsonResponseResult
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    /*public void pushMessageAll(PushModel pushModel) throws APIConnectionException, APIRequestException {
        JPushClient jPushClient = propertiesService.createJPushClient(pushModel.isApnsProduction());
        Platform all = null;
        if(pushModel.getDiviceType() == 0){//0为ios
            all = Platform.ios();
        }else if (pushModel.getDiviceType() == 2){//2为android
            all = Platform.android();
        }else if (pushModel.getDiviceType() == 3){//3为全部
            all = Platform.all();
        }else{//否则默认安卓和ios
            all = Platform.android_ios();
        }
        String[] strings = new String[pushModel.getIosAliases().length + pushModel.getAndriodAliases().length];
        PushPayload build = new PushPayload.Builder()
                .setPlatform(all)
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder().setAlert(pushModel.getContent()).build()).build();
        PushResult pushResult = jPushClient.sendPush(build);
        LOG.info("推送状态:"+pushResult.getResponseCode() + ",推送结果："+pushResult.getOriginalContent());
    }*/
}
