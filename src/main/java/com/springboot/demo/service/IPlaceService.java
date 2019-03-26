package com.springboot.demo.service;

import com.springboot.demo.entity.Place;

import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
public interface IPlaceService {

    //获取场地列表
    List<Place> getPlaceListByPage(Integer pageNum,Integer pageSize,Place place);

    boolean createPlace(Place place);
}
