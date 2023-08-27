package com.qiang.controller;


import com.qiang.domain.Course;
import com.qiang.domain.CourseLesson;
import com.qiang.domain.CourseSection;
import com.qiang.domain.ResponseResult;
import com.qiang.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(int courseId){
        List<CourseSection> listByCourseId = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", listByCourseId);
        return result;
    }
    /**
     * 回显章节对应的课程信息
     * */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId){
        Course courseByCourseId = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功",courseByCourseId);
        return result;
    }
    /**
     * 保存和修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        try {
            if (courseSection.getId() == null ){
                courseContentService.saveSection(courseSection);
                ResponseResult result = new ResponseResult(true, 200, "响应成功",null);
                return result;
            }else {
                courseContentService.updateSection(courseSection);
                ResponseResult result = new ResponseResult(true, 200, "响应成功",null);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status){
        courseContentService.updateSectionStatus(id,status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功",map);
        return result;
    }
    /**
     * 保存课程信息
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLseeon(@RequestBody CourseLesson lesson){
        try {
            courseContentService.saveLesson(lesson);
            ResponseResult result = new ResponseResult(true, 200, "响应成功",null);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 修改课程信息
     */
    @RequestMapping("/updateLesson")
    public ResponseResult updateLseeon(@RequestBody CourseLesson lesson){
        try {
            courseContentService.updateLesson(lesson);
            ResponseResult result = new ResponseResult(true, 200, "响应成功",null);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
