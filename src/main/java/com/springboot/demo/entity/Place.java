package com.springboot.demo.entity;

import java.io.Serializable;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description: Pojo for Place
 */
public class Place implements Serializable {

    private static final long serialVersionUID = -8259769283532285496L;

    private String placeId;//场地ID
    private String placeName;//场地名称
    private String placeType;//场地类型
    private String createBy;//创建者
    private String createTime;//创建时间
    private String isEff;//有效状态
    private String isDelete;//逻辑删除

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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
