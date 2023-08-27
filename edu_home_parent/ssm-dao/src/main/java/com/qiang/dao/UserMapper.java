package com.qiang.dao;

import com.qiang.domain.*;

import java.util.List;

public interface UserMapper {
    public List<User> findAllUserByPage(UserVo userVo);

    public void updateUserStatus(User user);

    /**
     * 保存用户信息
     */
    public void saveUser(User user);
    /**
     * 修改用户信息
     */
    public void updateUser(User user);

    /**
     * 用户登录
     */
    public User login(User user);

    //清空中间表
    public void deleteUserContextRole(Integer userId);
    //分配角色
    public void userContextRole(User_Role_relation userRoleRelation);

    /**
     * 1.根据ID查询用户当前角色集合
     */
    public List<Role> findUserRelationRoleById(Integer id);
    /**
     * 2.根据角色集合查询父菜单(-1)
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /**
     * 3.根据Pid查询子菜单
     */
    public List<Menu> findSubMenuByPid(Integer pid);
    /**
     * 4.根据角色集合查询资源列表
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
