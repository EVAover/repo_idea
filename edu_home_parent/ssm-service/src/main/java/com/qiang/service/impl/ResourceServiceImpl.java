package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.ResourceMapper;
import com.qiang.domain.Resource;
import com.qiang.domain.ResourceVo;
import com.qiang.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;
    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {
//        1获取当前页及大小
        PageHelper.startPage(resourceVo.getCurrentPage(), resourceVo.getPageSize());
        //2查询所有
        List<Resource> allResource = resourceMapper.findAllResource();
        //3封装
        PageInfo<Resource> pageInfo = new PageInfo<>(allResource);
        return pageInfo;
    }

    @Override
    public void saveResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy("system");
        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }
}
