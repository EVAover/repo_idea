package com.qiang.dao;

import com.qiang.domain.Resource;

import java.util.List;

public interface ResourceMapper {
    public List<Resource> findAllResource();

    public void saveResource(Resource resource);

    public void updateResource(Resource resource);

    public void deleteResource(Integer id);
}
