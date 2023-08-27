package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.PublicPageInfoVo;
import com.qiang.dao.MenuMapper;
import com.qiang.domain.Menu;
import com.qiang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 回显菜单信息(包括父子菜单的全部信息)
     *
     * @param pid
     * @return
     */
    @Override
    public List<Menu> findSubMenuListByPid(Integer pid) {
        List<Menu> menuList = menuMapper.findSubMenuListByPid(pid);
        return menuList;
    }

    @Override
    public PageInfo<Menu> findAllMenu(PublicPageInfoVo pageInfoVo) {
        //1拿到分页页数和大小
        PageHelper.startPage(pageInfoVo.getCurrentPage(), pageInfoVo.getPageSize());
        //2查询所有结果
        List<Menu> allMenu = menuMapper.findAllMenu();
        //3封装进分页方法中返回
        PageInfo<Menu> pageInfo = new PageInfo<>(allMenu);
        return pageInfo;
    }

    @Override
    public void saveMenu(Menu menu) {

        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("system");
        menuMapper.updateMenu(menu);
    }
}

