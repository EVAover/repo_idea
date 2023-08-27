package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.PublicPageInfoVo;
import com.qiang.domain.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> findSubMenuListByPid(Integer pid);
    public PageInfo<Menu> findAllMenu(PublicPageInfoVo pageInfoVo);

    public void saveMenu(Menu menu);

    public void updateMenu(Menu menu);
}
