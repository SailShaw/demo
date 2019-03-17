package com.springboot.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.Place;
import com.springboot.demo.mapper.PlaceMapper;
import com.springboot.demo.service.IPlaceService;
import com.springboot.demo.util.UUIDTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@Service
public class PlaceServiceImpl implements IPlaceService {

    @Resource
    private PlaceMapper placeMapper;


    @Override
    public List<Place> getPlaceListByPage(Integer pageNum, Integer pageSize, Place place) {
        //分页
        PageHelper.startPage(pageNum,pageSize);

        List<Place> result = placeMapper.getPlaceListByPage(place);

        return result;
    }

    @Override
    public void createPlace(Place place) {
        //生成ID
        place.setPlaceId(UUIDTool.getUUID());
//        long time = System.currentTimeMillis();
//        place.setCreateTime(String.valueOf(time));
        placeMapper.createPlace(place);

    }
}
