package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Resource;
import com.qiang.domain.ResourceVo;

public interface ResourceService {
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);

    public void saveResource(Resource resource);

    public void updateResource(Resource resource);

    public void deleteResource(Integer id);
}
