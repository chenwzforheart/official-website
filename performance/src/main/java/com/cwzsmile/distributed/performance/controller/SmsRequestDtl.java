package com.cwzsmile.distributed.performance.controller;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 短信请求记录表
 */
@Table(name = "t_msg_sms_request_dtl")
public class SmsRequestDtl {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 返回用户的批次号
     */
    private String smsRequestId;

    /**
     * 请求结果
     */
    private String requestResult;

    /**
     * 活动名
     */
    private String activityName;

    /**
     * 签名
     */
    private String signature;

    /**
     * 子号
     */
    private String subPort;

    /**
     * 默认0,1代表验证码，2代表通知，3代表营销，其他未知
     */
    private Integer noticeType;

    /**
     * 模板ID
     */
    private Long templateId;

    /**
     * 内容计数
     */
    private Integer countContent;

    /**
     * 人数计数
     */
    private Integer countCustomer;

    /**
     * 计数误差
     */
    private Integer deviation;

    /**
     * 国际电话区号
     */
    private String mobileAreaCode;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 发送来源
     */
    private Integer sendSource;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 逻辑删除：1-未删除 0-已删除
     */
    private String isValid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 内容
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmsRequestId() {
        return smsRequestId;
    }

    public void setSmsRequestId(String smsRequestId) {
        this.smsRequestId = smsRequestId == null ? null : smsRequestId.trim();
    }

    public String getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(String requestResult) {
        this.requestResult = requestResult == null ? null : requestResult.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getSubPort() {
        return subPort;
    }

    public void setSubPort(String subPort) {
        this.subPort = subPort == null ? null : subPort.trim();
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Integer getCountContent() {
        return countContent;
    }

    public void setCountContent(Integer countContent) {
        this.countContent = countContent;
    }

    public Integer getCountCustomer() {
        return countCustomer;
    }

    public void setCountCustomer(Integer countCustomer) {
        this.countCustomer = countCustomer;
    }

    public Integer getDeviation() {
        return deviation;
    }

    public void setDeviation(Integer deviation) {
        this.deviation = deviation;
    }

    public String getMobileAreaCode() {
        return mobileAreaCode;
    }

    public void setMobileAreaCode(String mobileAreaCode) {
        this.mobileAreaCode = mobileAreaCode == null ? null : mobileAreaCode.trim();
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource == null ? null : userSource.trim();
    }

    public Integer getSendSource() {
        return sendSource;
    }

    public void setSendSource(Integer sendSource) {
        this.sendSource = sendSource;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
