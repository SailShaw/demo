package com.springboot.demo.controller;

import com.springboot.demo.Common.PageBean;
import com.springboot.demo.entity.Place;
import com.springboot.demo.service.IPlaceService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create By SINYA
 * Description:
 */
@RestController
@RequestMapping("/place")
public class PlaceController {

    @Resource
    private IPlaceService placeService;


    /**
     * 获取场地列表
     * @return
     */
    @RequestMapping("/getPlaceListByPage")
    public PageBean<Place> getPlaceListByPage(HttpServletRequest request,Place place){
        //定义数据集
        List<Place> resultList = null;

        //分页
        try{
            // 获取分页参数
            String _pageNum = request.getParameter("pageNum");
            String _pageSize = request.getParameter("pageSize");
            //转型+判空
            Integer pageNum = StringUtils.isEmpty(_pageNum) ? 1 : Integer.parseInt(_pageNum);
            Integer pageSize = StringUtils.isEmpty(_pageSize) ? 7 : Integer.parseInt(_pageSize);

            resultList = placeService.getPlaceListByPage(pageNum,pageSize,place);
        }catch(Exception e){
            //logger
        }

        //Page对象

        PageBean<Place> pageBean = new PageBean<>(resultList);
        return pageBean;
    }

    /**
     * 添加场地
     * @param request
     * @param place
     * @return
     */
    @RequestMapping("/createPlace")
    public String createPlace(HttpServletRequest request,Place place){
        //从session里获取当前用户的名字
//        place.setCreateBy(request.getParameter("znName"));
        place.setCreateBy("Sinya");
        //执行
        placeService.createPlace(place);
        return "success";
    }
}
