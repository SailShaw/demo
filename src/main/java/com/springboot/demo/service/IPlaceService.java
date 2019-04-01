package com.springboot.demo.service;

import com.springboot.demo.entity.Place;

import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
public interface IPlaceService {

    /**
     * 获取资源清单
     * @param pageNum
     * @param pageSize
     * @param place
     * @return
     */
    List<Place> getPlaceListByPage(Integer pageNum,Integer pageSize,Place place);

    /**
     * 新增资源
     * @param place
     * @return
     */
    String createPlace(Place place);

    /**
     * 修改资源信息
     * @param place
     * @return
     */
    String modifyPlace(Place place);

    /**
     * 逻辑删除资源
     * @param place
     * @return
     */
    String deletePlace(Place place);

}
