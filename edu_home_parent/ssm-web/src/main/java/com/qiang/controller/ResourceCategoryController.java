package com.qiang.controller;

import com.qiang.domain.ResourceCategory;
import com.qiang.domain.ResponseResult;
import com.qiang.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }
}
