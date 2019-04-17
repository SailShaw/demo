package com.springboot.demo.entity;

import java.io.Serializable;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Domain for Application
 */
public class Application implements Serializable {


    private static final long serialVersionUID = 1117965240134220722L;

    private String formId;//表单ID
    private String userId;//用户ID
    private String groupId;//部门ID
    private String placeId;//资源ID

    private String activityTitle;//活动描述
    private String activityContent;//场地ID
    private String attendance;//参与人数
    private String startDate;//开始日期
    private String endDate;//结束日期

    private String mailMsg;//邮件内容
    private String verifyStatus;//审核状态
    private String isEff;//是否有效
    private String isOff;//是否关闭

    private String createTime;//创建日期
    private String updateTime;//更新日期
    private String verifyTime;//更新日期

    private String znName;//申请人
    private String reviewer;//审批人
    private String placeName;//场地名称



    /* Getter & Setter */

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMailMsg() {
        return mailMsg;
    }

    public void setMailMsg(String mailMsg) {
        this.mailMsg = mailMsg;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getIsEff() {
        return isEff;
    }

    public void setIsEff(String isEff) {
        this.isEff = isEff;
    }

    public String getIsOff() {
        return isOff;
    }

    public void setIsOff(String isOff) {
        this.isOff = isOff;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getZnName() {
        return znName;
    }

    public void setZnName(String znName) {
        this.znName = znName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
