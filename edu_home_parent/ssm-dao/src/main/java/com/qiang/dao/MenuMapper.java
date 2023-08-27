package com.qiang.dao;

import com.qiang.domain.Menu;

import java.util.List;

public interface MenuMapper {
    public List<Menu> findSubMenuListByPid(Integer pid);

    public List<Menu> findAllMenu();

    public void saveMenu(Menu menu);

    public void updateMenu(Menu menu);

}
