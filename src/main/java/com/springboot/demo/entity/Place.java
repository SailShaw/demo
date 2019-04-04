package com.springboot.demo.entity;

import java.io.Serializable;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Domain for Resources
 */
public class Place implements Serializable {

    private static final long serialVersionUID = -8259769283532285496L;

    private String placeId;//场地ID
    private String placeName;//场地名称
    private String placeType;//场地类型
    private String createBy;//创建者
    private String createTime;//创建时间
    private String updateBy;//更新者
    private String updateTime;//更新时间
    private String isEff;//有效状态
    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
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

    public String getIsEff() {
        return isEff;
    }

    public void setIsEff(String isEff) {
        this.isEff = isEff;
    }
}
