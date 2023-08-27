package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Resource;
import com.qiang.domain.ResourceVo;
import com.qiang.domain.ResponseResult;
import com.qiang.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVo);
        ResponseResult result = new ResponseResult(true,200,"响应成功",pageInfo);
        return result;
    }

    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        try {
            if (resource.getId() == null){
                resourceService.saveResource(resource);
                ResponseResult result = new ResponseResult(true,200,"响应成功",null);
                return result;
            }else {
                resourceService.updateResource(resource);
                ResponseResult result = new ResponseResult(true,200,"响应成功",null);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",null);
        return result;
    }
}
