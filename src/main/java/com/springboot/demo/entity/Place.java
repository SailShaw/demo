package com.springboot.demo.entity;

/**
 * Create By SINYA
 * Create Date 2019/2/27
 * Description: Pojo for Place
 */
public class Place {
    private String placeId;//场地ID
    private String placeName;//场地名称
    private String occupyStatus;//占用状态
    private String createBy;//创建者
    private String createTime;//创建时间
    private String isEff;//有效状态


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

    public String getOccupyStatus() {
        return occupyStatus;
    }

    public void setOccupyStatus(String occupyStatus) {
        this.occupyStatus = occupyStatus;
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
