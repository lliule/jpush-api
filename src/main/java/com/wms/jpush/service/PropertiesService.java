package com.wms.jpush.service;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesService {

    @Value("JPUSH_APP_KEY_WCS")
    private  static String JPUSH_APP_KEY_WCS ;

    @Value("JPUSH_MASTER_SECRET_WCS")
    private  static String JPUSH_MASTER_SECRET_WCS ;

    @Value("JPUSH_APP_KEY_YIYU")
    private  static String JPUSH_APP_KEY_YIYU ;

    @Value("JPUSH_MASTER_SECRET_YIYU")
    private  static String JPUSH_MASTER_SECRET_YIYU ;

    public JPushClient createIosJPushClient(boolean apnsProduction){
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setApnsProduction(apnsProduction);//设置服务环境，true:生产环境；flase:开发环境
        return new JPushClient(JPUSH_MASTER_SECRET_WCS, JPUSH_APP_KEY_WCS, null, clientConfig);
    }

    public JPushClient createAndriosJPushClient(boolean apnsProduction){
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setApnsProduction(apnsProduction);//设置服务环境，true:生产环境；flase:开发环境
        return new JPushClient(JPUSH_MASTER_SECRET_YIYU, JPUSH_APP_KEY_YIYU, null, clientConfig);
    }
}
