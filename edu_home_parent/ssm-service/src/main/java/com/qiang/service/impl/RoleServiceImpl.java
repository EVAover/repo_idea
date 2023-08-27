package com.qiang.service.impl;

import com.qiang.dao.RoleMapper;
import com.qiang.domain.Role;
import com.qiang.domain.RoleMenuVo;
import com.qiang.domain.Role_menu_relation;
import com.qiang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public void saveRole(Role role) {
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdatedTime(new Date());
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.updateRole(role);
    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        List<String> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
//        补齐信息
        Date date = new Date();
//        清空中间表
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        for (Integer mid : roleMenuVo.getMenuIdList()){

            Role_menu_relation roleMenuRelation = new Role_menu_relation(
                    null,mid, roleMenuVo.getRoleId(),date,date,"system","system");
            roleMapper.RoleContextMenu(roleMenuRelation);
        }
    }


    @Override
    public void deleteRole(Integer id) {
//        清空中间表
        roleMapper.deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);
    }
}
