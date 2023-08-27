package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.PublicPageInfoVo;
import com.qiang.domain.Menu;
import com.qiang.domain.ResponseResult;
import com.qiang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(PublicPageInfoVo pageInfoVo){
        PageInfo<Menu> pageInfo = menuService.findAllMenu(pageInfoVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", pageInfo);
        return result;
    }

    /**
     * 回显菜单信息(包括父子菜单的全部信息)
     */
    @RequestMapping("/findSubMenuListByPid")
    public ResponseResult findSubMenuListByPid( Integer id){
        if (id == -1){
            //添加操作 不需要回显父级菜单信息
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            HashMap<String , Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        }else {
            //修改
            List<Menu> menu = menuService.findSubMenuListByPid(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            HashMap<String , Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        }
    }

    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        try {
            if (menu.getId() == null){
                menuService.saveMenu(menu);
                ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
                return result;
            }else {
                menuService.updateMenu(menu);
                ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


}
