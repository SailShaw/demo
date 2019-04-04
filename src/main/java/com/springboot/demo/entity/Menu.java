package com.springboot.demo.entity;

import java.io.Serializable;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Domain for Menu
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 4007682221096174434L;

    private String roleId;//角色ID
    private String roleName;//角色名称

    private String permitId;//权限ID
    private String permitType;//权限名称
    private String permitDesc;//权限名称
    private String permitIcon;//权限地址
    private String permitUrl;//权限类型
    private String orderNum;//权限类型
    private String createBy;//创建时间
    private String createTime;//更新时间

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getPermitId() {
        return permitId;
    }

    public void setPermitId(String permitId) {
        this.permitId = permitId;
    }

    public String getPermitType() {
        return permitType;
    }

    public void setPermitType(String permitType) {
        this.permitType = permitType;
    }

    public String getPermitDesc() {
        return permitDesc;
    }

    public void setPermitDesc(String permitDesc) {
        this.permitDesc = permitDesc;
    }

    public String getPermitIcon() {
        return permitIcon;
    }

    public void setPermitIcon(String permitIcon) {
        this.permitIcon = permitIcon;
    }

    public String getPermitUrl() {
        return permitUrl;
    }

    public void setPermitUrl(String permitUrl) {
        this.permitUrl = permitUrl;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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
}
