package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.*;
import com.qiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo<User> pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", pageInfo);
        return result;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id,String status){
        userService.updateUserStatus(id,status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", status);
        return result;
    }

    @RequestMapping("/saveOrUpdateUser")
    public ResponseResult saveOrUpdateUser(@RequestBody User user){
        try {
            if (user.getId() == null ){
                userService.saveUser(user);
                ResponseResult result = new ResponseResult(true, 200, "响应成功",null);
                return result;
            }else {
                userService.updateUser(user);
                ResponseResult result = new ResponseResult(true, 200, "响应成功",null);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);
        ResponseResult result = null;
        if (login != null){
            //保存access_token
            Map<String,Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token",access_token);
            map.put("user_id",login.getId());
            HttpSession session = request.getSession();
            session.setAttribute("user_id",login.getId());
            session.setAttribute("access_token",access_token);
            result = new ResponseResult(true,200,"success",map);
        }else{
            result = new ResponseResult(true,1,"用户名密码错误",null);
        }
        return result;
    }

    /**
     * 根据ID查询用户当前角色
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        ResponseResult result = new ResponseResult(true, 200, "查询用户角色成功", roleList);
        return result;
    }
    //分配角色
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        ResponseResult result = new ResponseResult(true, 200, "分配角色成功", null);
        return result;
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的token
        String token = request.getHeader("Authorization");
        //获取session中的access_token
        HttpSession session = request.getSession();
        String  accessToken = (String) session.getAttribute("access_token");
        if (token.equals(accessToken)){
            Integer userId = (Integer) session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(userId);
            return result;
        }else {
            return new ResponseResult(true,400,"获取失败",null);
        }
    }

}
