package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.Md5;
import com.qiang.dao.UserMapper;
import com.qiang.domain.*;
import com.qiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id,String status) {
        User user = new User();
        user.setUpdate_time(new Date());
        user.setId(id);
        user.setStatus(status);
        userMapper.updateUserStatus(user);
    }

    @Override
    public void saveUser(User user) {
        user.setIs_del(0);
        Date date = new Date();
        user.setCreate_time(date);
        user.setUpdate_time(date);
        user.setStatus("ENABLE");
        user.setAccount_non_expired(1);
        user.setAccount_non_locked(1);
        user.setCredentials_non_expired(1);
        userMapper.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        Date date = new Date();
        user.setUpdate_time(date);
        userMapper.saveUser(user);
    }

    @Override
    public User login(User user) throws Exception {
        User login = userMapper.login(user);
        if (login != null && Md5.verify(user.getPassword(),"qiang",login.getPassword())){
            String md5 = Md5.md5("123456", "qiang");
            System.out.println(md5);
            return login;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        return roleList;
    }
//
//    @Override
//    public void deleteUserContextRole(Integer userId) {
//        userMapper.deleteUserContextRole(userId);
//    }

    @Override
    public void userContextRole(UserVo userVo) {
        //清空中间表
        userMapper.deleteUserContextRole(userVo.getUserId());

        for (Integer roleId : userVo.getRoleIdList() ){
            User_Role_relation userRoleRelation = new User_Role_relation();
            userRoleRelation.setUserId(userVo.getUserId());
            userRoleRelation.setRoleId(roleId);
            Date date = new Date();
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);
            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedby("system");
            userMapper.userContextRole(userRoleRelation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        //1.根据用户ID获取角色集合信息（包含了角色的所有信息）
        List<Role> roleList = userMapper.findUserRelationRoleById(userid);
        //2.遍历所有角色id，（不需要角色的其他信息）
        ArrayList<Integer> roleids = new ArrayList<>();
        for (Role role : roleList){
            roleids.add(role.getId());
        }
        //3.根据角色id集合查询父菜单
        List<Menu> menuList = userMapper.findParentMenuByRoleId(roleids);
        //4遍历查询子菜单封装
        for (Menu menu:menuList){
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        //5查询资源菜单
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleids);
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",menuList);
        map.put("resourceList",resourceList);
        //6封装数据
        return new ResponseResult(true,200,"获取信息成功",map);

    }
}
