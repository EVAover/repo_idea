package com.qiang.dao;


import com.qiang.domain.Role;
import com.qiang.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    public List<Role> findAllRole(Role role);

    /**
     * 新增
     */
    public void saveRole(Role role);
    /**
     * 修改
     */
    public void updateRole(Role role);
    /*
    根据角色ID查询菜单信息
*/
    public List<String> findMenuByRoleId(Integer roleId);

//    为角色分配菜单列表
    public void RoleContextMenu(Role_menu_relation role_menu_relation);
    //删除中间表信息
    public void deleteRoleContextMenu(Integer id);
//    删除角色
    public void deleteRole(Integer id);

}
