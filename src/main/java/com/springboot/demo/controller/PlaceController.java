package com.springboot.demo.controller;

import com.springboot.demo.core.model.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IPlaceService;
import com.springboot.demo.util.ObjectHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Controller for Resources
 */

@RestController
@RequestMapping("/place")
public class PlaceController {

    private final static Logger logger = LoggerFactory.getLogger(PlaceController.class);

    @Resource
    private IPlaceService placeService;


    /**
     * 获取场地列表
     *
     * @return
     */
    @Operation("获取资源清单")
    @RequestMapping("/getPlaceListByPage")
    public PageBean<Place> getPlaceListByPage(HttpServletRequest request, Place place) {
        //定义数据集
        List<Place> resultList = null;
        //分页
        try {
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;
            resultList = placeService.getPlaceListByPage(pageNum, pageSize, place);
        } catch (Exception e) {
            //logger
            logger.error("getPlaceListByPage" + e);
        }
        //Page对象
        PageBean<Place> pageBean = new PageBean<>(resultList);
        return pageBean;
    }

    /**
     * 添加场地
     *
     * @param request
     * @param place
     * @return
     */
    @Operation("添加资源")
    @RequestMapping("/createPlace")
    public String createPlace(HttpServletRequest request, Place place) throws IllegalAccessException {
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        String result = "";
        //判空
        if(ObjectHandle.reflectFieldIsNotALLNull(place,new String[]{"serialVersionUID"})){
            //设置创建者
            place.setCreateBy(userInfo.getZnName());
            result = placeService.createPlace(place);
        }else {
            logger.error("createPlace() -> Json is null");
            result = "JSON_IS_NULL";
        }
        return result;
    }


    /**
     * 修改资源信息
     *
     * @param place
     * @return
     */
    @Operation("修改资源信息")
    @RequestMapping("/modifyPlace")
    public String modifyPlace(HttpServletRequest request, Place place) throws IllegalAccessException {
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //执行
        String result = "";
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(place,new String[]{"serialVersionUID"})){
            //设置更新者
            place.setUpdateBy(userInfo.getZnName());
            result = placeService.modifyPlace(place);
        }else{
            result = "JSON_IS_NULL";
        }
        return result;
    }

    /**
     * 逻辑删除资源
     *
     * @param place
     * @return
     */
    @Operation("删除资源")
    @RequestMapping("/deletePlace")
    public String deletePlace(HttpServletRequest request,Place place) throws IllegalAccessException {
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //执行
        String result = "";
        if (ObjectHandle.reflectFieldIsNotALLNull(place,new String[]{"serialVersionUID"})) {
            //设置更新者
            place.setUpdateBy(userInfo.getZnName());
            result = placeService.deletePlace(place);
        }else {
            result = "JSON_IS_NULL";
        }
        return result;
    }

    @Operation("获取资源详情")
    @RequestMapping("/findPlaceById")
    public Place findPlaceById(HttpServletRequest request,Place place) throws IllegalAccessException {
        if (ObjectHandle.reflectFieldIsNotALLNull(place,new String[]{"serialVersionUID"})) {
            return placeService.findPlaceById(place);
        }else {
            return  null;
        }
    }
}
