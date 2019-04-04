package com.springboot.demo.mapper;

import com.springboot.demo.entity.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Mapper for Resources
 */

@Mapper
public interface PlaceMapper {
    /**
     * 获取资源清单
     * @param place
     * @return
     */
    List<Place> getPlaceListByPage(Place place);

    /**
     * 新增资源
     * @param place
     * @return
     */
    void createPlace(Place place);

    /**
     * 修改资源信息
     * @param place
     * @return
     */
    void modifyPlace(Place place);

    /**
     * 逻辑删除资源
     * @param place
     * @return
     */
    void deletePlace(Place place);

    /**
     * 获取资源下拉列表
     * @return
     */
    List<Place> getPlaceList();
}
