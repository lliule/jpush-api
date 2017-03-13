package com.wms.jpush.api;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import com.wms.jpush.service.JPushService;
import com.wms.jpush.service.model.PushModel;
import com.wms.jpush.util.model.JsonResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class JPushApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(JPushApi.class);

    /**
     * 注入JpushService
     */
    @Autowired
    private JPushService jPushService;

    /**
     * 根据aliases给android用户推送消息
     * 如果每组alias超过500,将会被分批发送
     * @param pushModel 接受推送的消息对象 eg:{"andriodAliases":["3541919","3700937"...],"title":"happy new year","content":"新年将近，微差事祝大家新年快乐！","diviceType":2}
     * @return JsonResponseResult  {"status":200,"errorMsg":"","code":"T","data":{"errorAlias":["3435592"],"successAlias":["3700937","3700937"]}}
     * @see com.wms.jpush.util.model.JsonResponseResult
     * @throws APIConnectionException
     * @throws APIRequestException
     * @throws UnsupportedEncodingException
     */
    public Object pushAndroidMessageByAliases(PushModel pushModel) throws APIConnectionException, APIRequestException, UnsupportedEncodingException {
        JsonResponseResult result = new JsonResponseResult();
        //校验aliases是否存在
        String[] aliases = pushModel.getAndriodAliases();
        if(aliases == null){
            LOGGER.error("aliases参数校验错误！aliases = {}", "");
            return JsonResponseResult.validatorErr(result);
        }
        //校验设备类型是不是andriod
        int diviceType = pushModel.getDiviceType();
        if(diviceType != 2){//判断设备的类型是不是android
            LOGGER.error("diviceType参数校验错误！diviceType = {},希望获取值是2",diviceType);
            return JsonResponseResult.validatorErr(result);
        }
        return jPushService.pushAndroidMessageByAliases(pushModel);
    }


    /**
     *根据aliases给ios用户推送消息
     * @param pushModel 接受推送的消息对象  eg:{"iosAliases":["3541919"],"title":"happy new year","content":"新年将近，微差事祝大家新年快乐！","diviceType":0}
     * @return JsonResponseResult {"status":200,"errorMsg":"","code":"T","data":"{\"sendno\":\"1745191019\",\"msg_id\":\"3038118609\"}"}
     * @see com.wms.jpush.util.model.JsonResponseResult
     * @throws APIConnectionException
     * @throws APIRequestException
     * @throws UnsupportedEncodingException
     */
    public Object pushIosMessageByAliases(PushModel pushModel) throws APIConnectionException, APIRequestException, UnsupportedEncodingException {
        JsonResponseResult result = new JsonResponseResult();
        String[] aliases = pushModel.getIosAliases();
        if(aliases == null || aliases.length == 0){
            LOGGER.error("aliases参数校验错误！aliases = {},希望获取值是2",aliases);
            return JsonResponseResult.validatorErr(result);
        }
        if(pushModel.getDiviceType() != 0){
            LOGGER.error("diviceType参数校验错误！diviceType = {},希望获取值是0",pushModel.getDiviceType());
            return JsonResponseResult.validatorErr(result);
        }
        result = jPushService.pushIosMessageByAlias(pushModel);
        return result;
    }

    /**
     *根据aliases给用户推送消息
     * @param pushModel 接受推送的消息对象 eg:{"iosAliases":["3541919"],"andriodAliases":["3541919"],"title":"happy new year","content":"新年将近，微差事祝大家新年快乐！","diviceType":0}
     * @return JsonResponseResult {"status":200,"errorMsg":"","code":"T","data":{}"}
     * @see com.wms.jpush.util.model.JsonResponseResult
     * @throws APIConnectionException
     * @throws APIRequestException
     * @throws UnsupportedEncodingException
     */
    public Object pushMessageByAliases(PushModel pushModel) throws APIConnectionException, APIRequestException, UnsupportedEncodingException, InterruptedException {
        JsonResponseResult result = new JsonResponseResult();
        jPushService.pushMessageByAliases(pushModel);
        return result;
    }
}
