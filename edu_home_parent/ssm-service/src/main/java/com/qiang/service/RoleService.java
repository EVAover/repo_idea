package com.qiang.service;

import com.qiang.domain.Role;
import com.qiang.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    public List<Role> findAllRole(Role role);
    /**
     * 新增角色
     */
    public void saveRole(Role role);
    /**
     * 修改角色
     */
    public void updateRole(Role role);

    public List<String> findMenuByRoleId(Integer roleId);

    //    为角色分配菜单列表
    public void RoleContextMenu(RoleMenuVo roleMenuVo);

    //    删除角色
    public void deleteRole(Integer id);

}
