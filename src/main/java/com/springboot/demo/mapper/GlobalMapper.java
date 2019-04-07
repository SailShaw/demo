package com.springboot.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Mapper for Resources
 */

@Mapper
public interface GlobalMapper {

    int applicationTotal();

    List<Map<String, String>> typeCount();

    int userCount();

    int placeCount();

    List<Map<String, String>>  twoWeekDataCount();

    List<Map<String, String>>  lastWeekNumCount();

}
