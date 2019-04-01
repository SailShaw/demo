package com.springboot.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.springboot.demo.entity.Place;
import com.springboot.demo.mapper.PlaceMapper;
import com.springboot.demo.service.IPlaceService;

import com.springboot.demo.util.SnowFlake;
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


    private SnowFlake snowFlake = new SnowFlake(1,9);


    /**
     * 获取场地清单
     * @param pageNum
     * @param pageSize
     * @param place
     * @return
     */
    @Override
    public List<Place> getPlaceListByPage(Integer pageNum, Integer pageSize, Place place) {
        //分页
        PageHelper.startPage(pageNum,pageSize);

        List<Place> result = placeMapper.getPlaceListByPage(place);
        return result;
    }

    /**
     * 新增场地
     * @param place
     * @return
     */
    @Override
    public boolean createPlace(Place place) {
        //生成ID
        place.setPlaceId(String.valueOf(snowFlake.nextId()));
        //执行
        if (placeMapper.createPlace(place)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 修改资源信息
     * @param place
     * @return
     */
    @Override
    public boolean modifyPlace(Place place) {
        //执行
        if (placeMapper.modifyPlace(place)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 逻辑删除资源
     * @param place
     * @return
     */
    @Override
    public boolean deletePlace(Place place) {
        //执行
        if (placeMapper.deletePlace(place)){
            return true;
        }else{
            return false;
        }
    }

}
