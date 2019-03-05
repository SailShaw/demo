package com.springboot.demo.mapper;

import com.springboot.demo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@Mapper
public interface MenuMapper {

    List<Menu> getMenuListByRole(Menu menu);


}
