package com.springboot.demo.controller;

import com.springboot.demo.service.IApplications;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Create By SINYA
 * Create Date 2019/3/1
 * Description: Controller For Application
 */

@RestController
public class ApplicationsController {

    @Resource
    private IApplications applications;

}
