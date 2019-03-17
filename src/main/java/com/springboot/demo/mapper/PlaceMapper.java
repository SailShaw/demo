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
    //
    List<Place> getPlaceListByPage(Place place);

    void createPlace(Place place);
}
