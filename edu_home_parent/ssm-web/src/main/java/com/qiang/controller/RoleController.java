package com.qiang.controller;

import com.qiang.domain.Menu;
import com.qiang.domain.ResponseResult;
import com.qiang.domain.Role;
import com.qiang.domain.RoleMenuVo;
import com.qiang.service.MenuService;
import com.qiang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> roleList = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true,200,"响应成功",roleList);
        return result;
    }

    /**
     * 查询所有菜单
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        //-1 表示查询所有菜单数据
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        try {
            if (role.getId() == null){
                roleService.saveRole(role);
                ResponseResult result = new ResponseResult(true,200,"响应成功",null);
                return result;
            }else {
                roleService.updateRole(role);
                ResponseResult result = new ResponseResult(true,200,"响应成功",null);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<String> list = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true,200,"响应成功",list);
        return result;
    }

    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);
        ResponseResult result = new ResponseResult(true,200,"响应成功","");
        return result;
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功","");
        return result;
    }

}
