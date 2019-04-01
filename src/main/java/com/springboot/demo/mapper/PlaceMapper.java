package com.springboot.demo.mapper;

import com.springboot.demo.entity.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create By SINYA
 * Description:
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
    boolean createPlace(Place place);

    /**
     * 修改资源信息
     * @param place
     * @return
     */
    boolean modifyPlace(Place place);

    /**
     * 逻辑删除资源
     * @param place
     * @return
     */
    boolean deletePlace(Place place);

    /**
     * 获取资源下拉列表
     * @return
     */
    List<Place> getPlaceList();
}
