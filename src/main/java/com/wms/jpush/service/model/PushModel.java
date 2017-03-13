package com.wms.jpush.service.model;

import com.wms.jpush.util.validator.NotNullForNumber;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Arrays;
import java.util.List;

public class PushModel {

    /**
     *设备类型.
     * 0-ios;2-andriod；3：all；其他，默认为ios和andriod
     * 参数校验信息:不能为空
     */
    @NotNullForNumber(message = "diviceType%E4%B8%8D%E8%83%BD%E4%B8%BA%E7%A9%BA")
    private Integer diviceType;//设备类型，0-ios;2-andriod；3：all

    /**
     * 标题.
     * 参数校验信息：不能为空。即使只对andriod有效
     */
    @NotEmpty(message = "title%E4%B8%8D%E8%83%BD%E4%B8%BA%E7%A9%BA")
    private String title;

    /**
     * 推送内容.
     * 参数校验信息：不能为空
     */
    @NotEmpty(message = "content%E4%B8%8D%E8%83%BD%E4%B8%BA%E7%A9%BA")
    private String content;

//    private String[] aliases;

    /**
     * andriod用户别名数组（别名即用户的设备id）
     */
    private String[] andriodAliases;

    /**
     * ios用户别名数组
     */
    private String[] iosAliases;

    private List<String> registrationIds;

    private String[] tags;

    /**
     * 推送的平台信息(暂无使用)
     */
    private String platform ;//推送的平台类型

    /**
     * 优先级（暂无使用）
     */
    private Integer priority = 0; //优先级

    /**
     * app环境；true为prd环境，false为stage环境；默认为prd
     */
    private Boolean apnsProduction =true;//环境设置，true为prd环境，false为stage环境；默认为prd

    public Integer getDiviceType() {
        return diviceType;
    }

    public void setDiviceType(Integer diviceType) {
        this.diviceType = diviceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setRegistrationIds(List<String> registrationIds) {
        this.registrationIds = registrationIds;
    }

    public List<String> getRegistrationIds() {
        return registrationIds;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Boolean isApnsProduction() {
        return apnsProduction;
    }

    public void setApnsProduction(Boolean apnsProduction) {
        this.apnsProduction = apnsProduction;
    }

    public String[] getAndriodAliases() {
        return andriodAliases;
    }

    public void setAndriodAliases(String[] andriodAliases) {
        this.andriodAliases = andriodAliases;
    }

    public String[] getIosAliases() {
        return iosAliases;
    }

    public void setIosAliases(String[] iosAliases) {
        this.iosAliases = iosAliases;
    }

    @Override
    public String toString() {
        return "PushModel{" +
                "diviceType=" + diviceType +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", andriodAliases=" + Arrays.toString(andriodAliases) +
                ", iosAliases=" + Arrays.toString(iosAliases) +
                ", registrationIds=" + registrationIds +
                ", tags=" + Arrays.toString(tags) +
                ", platform='" + platform + '\'' +
                ", priority=" + priority +
                ", apnsProduction=" + apnsProduction +
                '}';
    }
}
