package com.springboot.demo.controller;

import com.springboot.demo.core.interceptor.aop.Validate;
import com.springboot.demo.core.model.PageBean;
import com.springboot.demo.core.interceptor.aop.Operation;
import com.springboot.demo.core.model.ResultData;
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
    @Validate
    @Operation("获取资源清单")
    @RequestMapping("/getPlaceListByPage")
    public ResultData getPlaceListByPage(HttpServletRequest request, Place place) {
        logger.info("getPlaceListByPage() -> Begin");
        //定义数据集
        ResultData resultData = new ResultData();
        List<Place> resultList = null;
        try {
            // 获取分页参数
            Integer pageNum = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = 9;
            resultList = placeService.getPlaceListByPage(pageNum, pageSize, place);
        } catch (Exception e) {
            logger.error("getPlaceListByPage" + e);
        }
        //Page对象
        PageBean<Place> pageBean = new PageBean<>(resultList);
        resultData.setData(pageBean);
        logger.info("getPlaceListByPage() -> End");
        return resultData;
    }

    /**
     * 添加场地
     *
     * @param request
     * @param place
     * @return
     */
    @Validate
    @Operation("添加资源")
    @RequestMapping("/createPlace")
    public ResultData createPlace(HttpServletRequest request, Place place) throws IllegalAccessException {
        logger.info("createPlace() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(place, new String[]{"serialVersionUID"})) {
            //设置创建者
            place.setCreateBy(userInfo.getZnName());
            placeService.createPlace(place);
            resultData.setMessage("添加成功");
        } else {
            logger.error("createPlace() -> 10001:USER_NOT_LOGIN");
            resultData.setCode(10001);
            resultData.setMessage("未登录");
        }
        logger.info("getPlaceListByPage() -> End");
        return resultData;
    }


    /**
     * 修改资源信息
     *
     * @param place
     * @return
     */
    @Validate
    @Operation("修改资源信息")
    @RequestMapping("/modifyPlace")
    public ResultData modifyPlace(HttpServletRequest request, Place place) throws IllegalAccessException {
        logger.info("modifyPlace() -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(place, new String[]{"serialVersionUID"})) {
            //设置更新者
            place.setUpdateBy(userInfo.getZnName());
            placeService.modifyPlace(place);
            resultData.setMessage("修改成功");
        } else {
            logger.error("createPlace() -> 10001:USER_NOT_LOGIN");
            resultData.setCode(10001);
            resultData.setMessage("未登录");
        }
        logger.info("modifyPlace() -> End");
        return resultData;
    }

    /**
     * 逻辑删除资源
     *
     * @param place
     * @return
     */
    @Validate
    @Operation("删除资源")
    @RequestMapping("/deletePlace")
    public ResultData deletePlace(HttpServletRequest request, Place place) throws IllegalAccessException {
        logger.info(" -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(place, new String[]{"serialVersionUID"})) {
            //设置更新者
            place.setUpdateBy(userInfo.getZnName());
            place.setIsEff("1");
            placeService.deletePlace(place);
            resultData.setMessage("删除成功");
        } else {
            logger.error("createPlace() -> 10001:USER_NOT_LOGIN");
            resultData.setCode(10001);
            resultData.setMessage("未登录");
        }
        logger.info("modifyPlace() -> End");
        return resultData;
    }

    /**
     * 根据ID获取资源详情
     *
     * @param request
     * @param place
     * @return
     * @throws IllegalAccessException
     */
    @Validate
    @Operation("获取资源详情")
    @RequestMapping("/findPlaceById")
    public ResultData findPlaceById(HttpServletRequest request, Place place) throws IllegalAccessException {
        logger.info(" -> Begin");
        ResultData resultData = new ResultData();
        //从session里获取当前用户的名字
        User userInfo = (User) request.getSession().getAttribute("user");
        //判空
        if (ObjectHandle.reflectFieldIsNotALLNull(place, new String[]{"serialVersionUID"})) {
            resultData.setData(placeService.findPlaceById(place));
        } else {
            logger.error("createPlace() -> 10001:USER_NOT_LOGIN");
            resultData.setCode(10001);
            resultData.setMessage("未登录");
        }
        logger.info("findPlaceById() -> End");
        return resultData;
    }
}
