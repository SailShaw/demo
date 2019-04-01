package com.springboot.demo.controller;

import com.springboot.demo.core.common.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.entity.Place;
import com.springboot.demo.entity.User;
import com.springboot.demo.service.IPlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(PlaceController.class);

    @Resource
    private IPlaceService placeService;


    /**
     * 获取场地列表
     *
     * @return
     */
    @Operation(value = "获取场地列表")
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
    @Operation(value = "添加场地")
    @RequestMapping("/createPlace")
    public String createPlace(HttpServletRequest request, Place place) {
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //设置创建者
        place.setCreateBy(userInfo.getZnName());
        //执行
        String result = "";
        if (placeService.createPlace(place)) {
            result = "success";
        } else {
            result = "error";
        }
        return result;
    }


    /**
     * 修改资源信息
     * @param place
     * @return
     */
    @Operation("修改资源信息")
    @RequestMapping("modifyPlace")
    public String modifyPlace(Place place) {
        //执行
        String result = "";
        if (placeService.modifyPlace(place)) {
            result = "success";
        } else {
            result = "error";
        }
        return result;
    }

    /**
     * 逻辑删除资源
     * @param place
     * @return
     */
    @Operation("逻辑删除资源")
    @RequestMapping("deletePlace")
    public String deletePlace(Place place) {
        //执行
        String result = "";
        if (placeService.deletePlace(place)) {
            result = "success";
        } else {
            result = "error";
        }
        return result;
    }

}
