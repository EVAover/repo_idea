package com.qiang.service.impl;

import com.qiang.dao.ResourceCategoryMapper;
import com.qiang.domain.ResourceCategory;
import com.qiang.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    ResourceCategoryMapper resourceCategoryMapper;
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> list = resourceCategoryMapper.findAllResourceCategory();
        return list;
    }
}
