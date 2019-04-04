package com.springboot.demo.mapper;

import com.springboot.demo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Mapper for Menu
 */

@Mapper
public interface MenuMapper {

    List<Menu> getMenuListByRole(Menu menu);


}
