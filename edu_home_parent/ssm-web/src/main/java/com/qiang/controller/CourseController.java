package com.qiang.controller;

import com.qiang.domain.Course;
import com.qiang.domain.CourseVo;
import com.qiang.domain.ResponseResult;
import com.qiang.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping("/findCourseByConditioin")
    public ResponseResult findCourseByConditioin(@RequestBody CourseVo courseVo) {
        List<Course> courseList = courseService.findCourseByConditioin(courseVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);
        return result;
    }

    /**
     * 图片上传接口
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            //1.判断文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }
            //2.获取项目部署路径
            // D:\apache-tomcat-8.xxx\webapps\ssm_web\
            String realPath = request.getServletContext().getRealPath("/");
            String webappsPath = realPath.substring(0, realPath.indexOf("ssm_web"));

            //3.获取原文件名
            String filename = file.getOriginalFilename();
            //4.新文件名
            String newFileName = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
            //5.上传文件
            String uploadPath = webappsPath + "upload\\";
            File filePath = new File(uploadPath, newFileName);
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录：" + filePath);
            }
//            图片就进行了真正的上传
            file.transferTo(filePath);
            //6.将文件名和文件路径返回
            HashMap<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", "http://localhost:8080/upload/" + newFileName);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存&修改课程信息接口
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) {
        try {
            if (courseVo.getId() == null) {
                courseService.saveCourseOrTeacher(courseVo);
                ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
                return result;
            } else {
                courseService.updateCourseOrTeacher(courseVo);
                ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 查询（回显）课程信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(int id){
        CourseVo courseById = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseById);
        return result;
    }

    /**
     * 修改课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id, int status){
     courseService.updateCourseStatus(id,status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功",map);
        return result;
    }


}
