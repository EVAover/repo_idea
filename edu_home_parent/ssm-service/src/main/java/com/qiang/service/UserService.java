package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.ResponseResult;
import com.qiang.domain.Role;
import com.qiang.domain.User;
import com.qiang.domain.UserVo;

import java.util.List;

public interface UserService {
    /**
     * 多条件联合查询
     *
     * @param userVo
     * @return
     */
    public PageInfo<User> findAllUserByPage(UserVo userVo);

    public void updateUserStatus(int id,String status);

    /**
     * 保存用户信息
     */
    public void saveUser(User user);
    /**
     * 修改用户信息
     */
    public void updateUser(User user);
    //用户登录
    public User login(User user) throws Exception;

    /**
     * 根据ID查询用户当前角色
     */
    public List<Role> findUserRelationRoleById(Integer id);
    //清空中间表
//    public void deleteUserContextRole(Integer userId);
    //分配角色
    public void userContextRole(UserVo userVo);

    /**
     * 获取用户权限，进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userid);
}
