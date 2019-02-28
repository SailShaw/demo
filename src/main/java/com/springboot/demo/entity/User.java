package com.springboot.demo.entity;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description: Pojo for User
 */
public class User {

    private String userId;//用户ID
    private String account;//账号
    private String passcode;//密码
    private String znName;//姓名
    private String email;//邮箱
    private String telphone;//联系方式
    private String validFlag;//有效状态
    private String groupDesc;//组织描述



    private String createBy;//创建者
    private String createTime;//创建日期
    private String updateBy;//更新者
    private String updateTime;//更新日期

    private String roleId;//角色ID
    private String roleName;//角色名称
    private String roleStatus;//角色状态


    private String groupId;//组织ID
    private String groupName;//组织名称
    private String isEff;//有效状态

    /* Getter & Setter*/

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getZnName() {
        return znName;
    }

    public void setZnName(String znName) {
        this.znName = znName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIsEff() {
        return isEff;
    }

    public void setIsEff(String isEff) {
        this.isEff = isEff;
    }
}
